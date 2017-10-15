package com.cnframe.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cnframe.model.CommModel;


@Entity
@Table(name = "Sys_Role")
public class SysRole extends CommModel{
	private static final long serialVersionUID = 1L;
	@Column(name = "ROLECODE" , nullable = false)
	private String roleCode;
	@Column(name = "ROLENAME" , nullable = false)
	private String roleName;
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
}
