package com.cnframe.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cnframe.model.AbstractModel;

@Entity
@Table(name = "Sys_Dict")
public class SysDict extends AbstractModel{
	private static final long serialVersionUID = 1L;
	@Column(name = "DICTCODE")
	private String dictCode;
	@Column(name = "DICTNAME")
	private String dictName;
	@Column(name = "RIGHTCONTROL")
	private Long rightControl;//是否受权限控制0否1是
	@Column(name = "SORT")
	private Long sort;
	@Column(name = "REMARK", length=4000)
	private String remark;
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	public Long getRightControl() {
		return rightControl;
	}
	public void setRightControl(Long rightControl) {
		this.rightControl = rightControl;
	}
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
