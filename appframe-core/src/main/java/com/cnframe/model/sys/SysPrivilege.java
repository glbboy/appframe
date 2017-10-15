package com.cnframe.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cnframe.model.CommModel;

@Entity
@Table(name = "SYS_PRIVILEGE")
/**
 * 1、privilegeMaster为USER,privilegeAccess为ROLE时，表示用户和角色关系
 * 2、privilegeMaster为ROLE,privilegeAccess为MENU时，表示角色和模块功能权限关系
 * 3、privilegeMaster为ROLE,privilegeAccess为DATA时，表示角色和数据权限关系
 * 4、privilegeOperation为具体权限类型，暂时不用，涉及具体扩充权限时可能会用到
 *
 */
public class SysPrivilege extends CommModel{
	private static final long serialVersionUID = 1L;
	@Column(name = "PRIVILEGEMASTER" , nullable = false)
	private String privilegeMaster;
	@Column(name = "PRIVILEGEMASTERVALUE" , nullable = false)
	private String privilegeMasterValue;
	@Column(name = "PRIVILEGEACCESS" , nullable = false)
	private String privilegeAccess;
	@Column(name = "PRIVILEGEACCESSVALUE" , nullable = false)
	private String privilegeAccessValue;
	@Column(name = "PRIVILEGEOPERATION")
	private String privilegeOperation;
	public String getPrivilegeMaster() {
		return privilegeMaster;
	}
	public void setPrivilegeMaster(String privilegeMaster) {
		this.privilegeMaster = privilegeMaster;
	}
	public String getPrivilegeMasterValue() {
		return privilegeMasterValue;
	}
	public void setPrivilegeMasterValue(String privilegeMasterValue) {
		this.privilegeMasterValue = privilegeMasterValue;
	}
	public String getPrivilegeAccess() {
		return privilegeAccess;
	}
	public void setPrivilegeAccess(String privilegeAccess) {
		this.privilegeAccess = privilegeAccess;
	}
	public String getPrivilegeAccessValue() {
		return privilegeAccessValue;
	}
	public void setPrivilegeAccessValue(String privilegeAccessValue) {
		this.privilegeAccessValue = privilegeAccessValue;
	}
	public String getPrivilegeOperation() {
		return privilegeOperation;
	}
	public void setPrivilegeOperation(String privilegeOperation) {
		this.privilegeOperation = privilegeOperation;
	}
}
