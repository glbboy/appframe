package com.cnframe.model.sys;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cnframe.model.AbstractModel;

@Entity
@Table(name = "Sys_User_Log")
public class SysUserLog extends AbstractModel{
	private static final long serialVersionUID = 1L;
	@Column(name = "LOGLEVEL")
	private String logLevel;
	@Column(name = "USERCODE")
	private String userCode;
	@Column(name = "FUNID")
	private String funId;
	@Column(name = "LOGMAIN")
	private String logMain;
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name = "LOGCONTENT")
	private String logContent;
	@Column(name = "LOGTIME", insertable = false,updatable = false)
	@Temporal(TemporalType.DATE)
	private Date logTime;
	@Column(name = "MONTH", insertable = false,updatable = false)
	private String month;
	public String getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getFunId() {
		return funId;
	}
	public void setFunId(String funId) {
		this.funId = funId;
	}
	public String getLogMain() {
		return logMain;
	}
	public void setLogMain(String logMain) {
		this.logMain = logMain;
	}
	public String getLogContent() {
		return logContent;
	}
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
}
