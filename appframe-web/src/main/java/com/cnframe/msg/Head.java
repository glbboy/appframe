package com.cnframe.msg;

import java.io.Serializable;

public class Head implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userId;
	private String userCode;
	private String userName;
	private String userPass;
	private String sessionId;
	private String functionId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
}
