package com.cnframe.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cnframe.model.CommModel;

@Entity
@Table(name = "Sys_Model")
public class SysModel extends CommModel{
	private static final long serialVersionUID = 1L;
	@Column(name = "MODEL" , nullable = false)
	private String model;
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
	@Column(name = "SORT")
	private Integer sort;
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
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
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	
}
