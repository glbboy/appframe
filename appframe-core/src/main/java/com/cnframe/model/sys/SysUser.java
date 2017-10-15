package com.cnframe.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cnframe.model.CommModel;

@Entity
@Table(name = "Sys_User")
//@org.hibernate.annotations.DynamicUpdate
//@org.hibernate.annotations.DynamicInsert
//@org.hibernate.annotations.Entity(dynamicUpdate=true,dynamicInsert=true)

public class SysUser extends CommModel{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "USERCODE" , nullable = false)
	private String userCode;
	@Column(name = "USERNAME" , nullable = false)
	private String userName;
	@Column(name = "USERPASS" , nullable = false)
	private String userPass;
	@Column(name = "DEPTCODE")
	private String deptCode;
	@Column(name = "EMPLOYEECODE")
	private String employeeCode;
	
	@Column(name = "USERTYPE")
	private String userType;
	
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
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}
