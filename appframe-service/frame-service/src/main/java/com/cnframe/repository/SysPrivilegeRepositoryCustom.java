package com.cnframe.repository;

import java.util.List;

import com.cnframe.model.sys.SysMenu;
import com.cnframe.model.sys.SysRole;

public interface SysPrivilegeRepositoryCustom{
	/**
	 * 根据用户编码获取角色列表
	 */
	public List<SysRole> findRoleListByUserCode(String userCode);
	/**
	 * 根据角色编码获取功能权限列表
	 */
	public List<SysMenu> findMenuListByRoleCode(String roleCode);
}
