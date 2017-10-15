package com.cnframe.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cnframe.model.AbstractModel;
@Entity
@Table(name = "Sys_Field")
public class SysField extends AbstractModel{
	private static final long serialVersionUID = 1L;
	@Column(name = "TABLEID")
	private Long tableId;//表ID（对应SYS_TABLE_COMP-ID）
	
	@Column(name = "TYPE" , nullable = false)
	private String type;
	@Column(name = "LABEL" , nullable = false)
	private String label;
	@Column(name = "NAME")
	private String name;
	@Column(name = "PLACEHOLDER")
	private String placeHolder;
	@Column(name = "CLASSNAME")
	private String className;
	@Column(name = "REQUIRED")
	private Integer required;
	@Column(name = "MINLENGTH")
	private Integer minLength;
	@Column(name = "MAXLENGTH")
	private Integer maxLength;
	@Column(name = "PATTERN")
	private String pattern;
	
	@Column(name = "DICTTYPE")
	private Integer dictType;
	@Column(name = "DICTCODE")
	private String dictCode;
	@Column(name = "ERRMSG")
	private String errMsg;//错误提示信息
	@Column(name = "DEFAULTVALUE")
	private String defaultValue;//默认值
	@Column(name = "CANMODIFY")
	private Long canModify;//是否能修改
	@Column(name = "FORMHIDE")
	private Integer formHide;//0显示 1隐藏
	@Column(name = "GRIDHIDE")
	private Integer gridHide;//0显示 1隐藏
	@Column(name = "GRIDWIDTH")
	private Integer gridWidth;//grid宽度
	@Column(name = "QUERYCOND")
	private Integer queryCond;
	@Column(name = "SORT")
	private Long sort;
	public Long getTableId() {
		return tableId;
	}
	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlaceHolder() {
		return placeHolder;
	}
	public void setPlaceHolder(String placeHolder) {
		this.placeHolder = placeHolder;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Integer getRequired() {
		return required;
	}
	public void setRequired(Integer required) {
		this.required = required;
	}
	public Integer getMinLength() {
		return minLength;
	}
	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}
	public Integer getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public Integer getDictType() {
		return dictType;
	}
	public void setDictType(Integer dictType) {
		this.dictType = dictType;
	}
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public Long getCanModify() {
		return canModify;
	}
	public void setCanModify(Long canModify) {
		this.canModify = canModify;
	}
	public Integer getGridHide() {
		return gridHide;
	}
	public void setGridHide(Integer gridHide) {
		this.gridHide = gridHide;
	}
	public Integer getGridWidth() {
		return gridWidth;
	}
	public void setGridWidth(Integer gridWidth) {
		this.gridWidth = gridWidth;
	}
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
	}
	public Integer getFormHide() {
		return formHide;
	}
	public void setFormHide(Integer formHide) {
		this.formHide = formHide;
	}
	public Integer getQueryCond() {
		return queryCond;
	}
	public void setQueryCond(Integer queryCond) {
		this.queryCond = queryCond;
	}
	
}
