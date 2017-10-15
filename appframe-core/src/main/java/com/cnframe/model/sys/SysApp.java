package com.cnframe.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cnframe.model.AbstractModel;

@Entity
@Table(name = "SYS_APP")
public class SysApp  extends AbstractModel{
	private static final long serialVersionUID = 1L;
	@Column(name = "APPCODE", nullable = false)
	private String appCode;
	@Column(name = "APPNAME", nullable = false)
	private String appName;
	@Column(name = "SORT")
	private Long sort;
	@Column(name = "REMARK", length=4000)
	private String remark;
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
