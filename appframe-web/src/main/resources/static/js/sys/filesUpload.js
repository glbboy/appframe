define(function () {  
    "use strict";  
    var $updatedNum = $("#updated"),//已上传数  
        $totalNum = $("#total"),  
        $updating = $("#updating"),  
        $filesTable = null,  
        $filesTableDiv = null,  
        xhr_process,//显示进程  
        curUploadingRow,  
        operateEvents;//表格中的操作方法绑定的参数  
  
     // 初始化表格只有删除操作项  
    function operateDelete() {  
        return [  
            '<a class="fileinput-remove fileinput-remove-button" href="javascript:void(0)" title="删除">',  
            '<i class="icon-default icon-delete-active"></i>',  
            '</a>'  
        ].join('');  
    }  
  
      
    //修改表格中的操作列表的删除  
    function tableDelBind(num) {  
        $filesTable.bootstrapTable("updateCell", {  
            index : num,  
            field : 'operate',  
            value : operateDelete()  
        });  
    }   
      
    //更新表格的状态列表  
    function updateStateCell(num, stateValue) {  
        $filesTable.bootstrapTable("updateCell", {  
            index : num,  
            field : 'state',  
            value : stateValue  
        });  
    }   
  
     //单文件上传  
    function fileUpload(filesObj,i) {  
        var fd,  
            uploadUrl = "<span style="color:#ff6666;">自己需要上传的地址</span>",  
            updatedNum; //已上传数  
        if (i === filesObj.length) {  
            //最后一个传完 设置确定按钮可用  
            $("#dialogOk").removeClass("disabled").prop("disabled", false);  
            return;  
        }   
        fd = new FormData();  
        fd.append('file', filesObj[i]);  
        curUploadingRow = i;  
        $.ajax({  
            url : uploadUrl,  
            type : 'POST',  
            data : fd,  
            cache : false,  
            contentType : false,  
            processData : false,  
            xhr : xhr_process,  
            success : function (result) {  
                // 上传成功的数字加一  
                updatedNum = parseInt($updatedNum.text(), 10) + 1;  
                $updatedNum.html(updatedNum);  
                // 给当前行绑定一个上传成功后的 ID  
                $filesTable.bootstrapTable("updateCell", {  
                    index : i,  
                    field : 'id',  
                    value : result.files[0].id  
                });  
                updateStateCell(i, "上传完成");  
                tableDelBind(i);  
                setTimeout(function () {  
                    fileUpload(filesObj, backNameList, i + 1);  
                }, 0);  
            },  
            error : function (result) {                          
                updateStateCell(i, "<span class='red'>上传失败</span>");  
                setTimeout(function () {  
                    fileUpload(filesObj, backNameList, i + 1);  
                }, 0);  
            }  
        });              
    }  
  
    // 多文件上传  
    function uploadFilesAjax(filesObj, backNameList) {  
        fileUpload(filesObj, backNameList, 0);  
    }  
  
    /** 
     * 侦查附件上传情况 
     */  
    function uploadProgress(evt) {  
        var loaded = evt.loaded, // 已经上传大小情况  
            total = evt.total, // 附件总大小  
            percent = Math.floor(100 * loaded / total); // 已经上传的百分比  
        updateStateCell(curUploadingRow, percent + "%");  
    }  
  
    // 显示上传进度  
    xhr_process = function () {  
        var xhr = $.ajaxSettings.xhr();  
        if (uploadProgress && xhr.upload) {  
            xhr.upload.addEventListener("progress", uploadProgress, false);  
        }  
        return xhr;  
    };  
  
    function operateSize(value) {  
        var size = "";  
        if (value > 1048576) {  
            size = (value / 1024 / 1024).toFixed(1) + " MB";  
        } else {  
            if (value > 1024) {  
                size = parseInt(value / 1024, 10) + " KB";  
            } else {  
                size = "< 1KB";  
            }  
        }  
        return size;  
    }  
  
    //删除  
    function deleteData(fId, index) {  
        var delUrl = "<span style="color:#ff0000;">写自己的地址</span>" + fId;  
        if (fId !== "") {  
            //先删除数据库数据  
            $.ajax({  
                url : delUrl,  
                type : 'DELETE',  
                cache : false,  
                async : false,//同步删除的原因 由于在点击取消按钮的时候必须同时上传上传的所有文件  
                contentType : false,  
                processData : false,  
                success : function (result) {  
                    $filesTable.bootstrapTable('removeByUniqueId', [index]);  
                },  
                error : function (data) {  
                    BootstrapDialog.alert({  
                        title : "操作提示",  
                        message : "删除失败"  
                    });  
                }  
            });  
        } else {  
            $filesTable.bootstrapTable('removeByUniqueId', [index]);  
        }  
    }  
  
    operateEvents ={  
        'click .fileinput-remove' : function (e, value, row, index) {  
            deleteData(row.id, row.index);  
        }  
    };  
  
    // 初始化上传文弹出框的表格  
    function initUploadTable(dataObj) {  
        $filesTable = $("#filesTable");  
        // 绑定uploadFilesDiv filesTable dataObj  
        $filesTable.bootstrapTable({  
            columns : [{  
                field : 'id',  
                title : '文件id'  
            }, {  
                field : 'index',  
                title : '序号'  
            }, {  
                field : 'name',  
                title : '文件名称'  
            }, {  
                field : 'size',  
                title : '大小',  
                formatter : operateSize  
            }, {  
                field : 'state',  
                title : '状态'  
            }, {  
                field : 'operate',  
                title : '操作',  
                events : operateEvents  
            }  
                ],  
            data : dataObj,  
            rowStyle: rowStyle  
        });  
        $filesTable.bootstrapTable('hideColumn', 'id');  
    }  
  
    function bindEvent() {  
        $filesTableDiv = $("#filesTableDiv");  
        // 上传文件 filebatchselected  
        $("#uploadBtn").on('filebatchselected', function (event, files) {  
            var Tabledata = [], // 表格中数据集合  
            // 依次表格中的tr数据集合,上传总数，已上传数，'-1'代表上传的路径，进程  
                dataItem = null,  
                total = files.length,  
                updated = 0,  
                process = "0%",  
                i = 0,  
                getId = [],  
                getIdLength,  
                uploadFileDialog = null;  
            //验证文件总数不能超过50个  
            if (total > 50) {  
                BootstrapDialog.alert({  
                    title : "消息提醒",  
                    message : "已选择文件超过一次最多可上传50个数量限制，点击确定后需重新上传文件"  
                });  
            } else {  
                for (i = 0; i < total; i += 1) {  
                    dataItem = {  
                        'id' : '',  
                        'index' : i + 1,  
                        'name' : files[i].name,  
                        'size' : files[i].size,  
                        'state' : process  
                    };  
                    //绑定到上传文件列表的 集合  
                    Tabledata.push(dataItem);  
                }  
                // 显示弹出框  
                uploadFileDialog = new BootstrapDialog({  
                    title : function () {  
                        $updatedNum.html(updated);  
                        $totalNum.html("/" + total);  
                        return $updating;  
                    },  
                    cssClass : 'dialogShow',  
                    closable : false,  
                    closeByBackdrop : false,  
                    message : function () {  
                        // 绑定table数据，后返回table对象  
                        initUploadTable(Tabledata);  
                        return $filesTableDiv;  
                    },  
                    type : BootstrapDialog.TYPE_DEFAULT,  
                    draggable : true,  
                    buttons : [{  
                        id : 'dialogOk',  
                        label : '确定',  
                        cssClass : 'btn-primary disabled',  
                        autospin : false,  
                        action : function (dialog) {  
                            dialog.close();  
                        }  
                    }, {  
                        id : 'dialogCancel',  
                        label : '取消',  
                        cssClass : 'btn-default',  
                        autospin : false,  
                        action : function (dialog) {  
                            $("#sameNameDiv").addClass("hidden");  
                            //删除所有已上传的文件  
                            //获取上传后文件的ID  
                            getId = $filesTable.bootstrapTable('getOptions').data;  
                            getIdLength = getId.length;  
                            for (i = getIdLength - 1; i >= 0; i -= 1) {  
                                if (getId[i] !== undefined) {  
                                    deleteData(getId[i].id, getId[i].index);  
                                }  
                            }  
                           //是否需要取消所有上传文件  
                            dialog.close();  
                        }  
                    }],   
                    callback : function (result) {}  
                });  
                uploadFileDialog.open();  
            }  
        });  
    }  
  
    function start() {  
        bindEvent();  
    }  
      
    return {  
        start : start  
    };  
}); 