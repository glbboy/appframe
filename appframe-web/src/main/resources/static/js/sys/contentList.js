define(function () {  
    "use strict";  
    function init() {   
        //加载上传页面  
        $("#uploadFilesDiv").html("");  
        $("#uploadFilesDiv").load("mcm/filesUpload.html");           
        // 绑定上传组件  
        $("#uploadBtn").fileinput({  
            language : "ZH",  
            showCaption : false,  
            showUpload : false,  
            showRemove : false,  
            maxFilesNum : 50, // 最多文件数量  
            overwriteinitial : false,  
            allowedFileTypes : ["png", "jpg", "gif"],  
            browseClass : "btn btn-info"  
        });  
        // 修改上传文件按钮的文字-根据需求定义  
        $("#uploadBtn").prev("span.hidden-xs").html("上传文件");  
    }  
      
    function start() {  
        init();  
    }  
    return {  
        start : start  
    };  
});  