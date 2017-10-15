package com.cnframe.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cnframe.model.AbstractModel;

@Entity
@Table(name = "Sys_Dict_Detail")
public class SysDictDetail extends AbstractModel{
	private static final long serialVersionUID = 1L;
	@Column(name = "DICTCODE")
	private String dictCode;
	@Column(name = "DICTVALUE")
	private String dictValue;
	@Column(name = "DICTFLAG")
	private String dictFlag;
	@Column(name = "DICTDESC")
	private String dictDesc;
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
	
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
	}
	public String getDictValue() {
		return dictValue;
	}
	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}
	public String getDictDesc() {
		return dictDesc;
	}
	public void setDictDesc(String dictDesc) {
		this.dictDesc = dictDesc;
	}
	public String getDictFlag() {
		return dictFlag;
	}
	public void setDictFlag(String dictFlag) {
		this.dictFlag = dictFlag;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
