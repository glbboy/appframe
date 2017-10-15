package com.cnframe.service;

import java.util.List;

import com.cnframe.model.sys.SysMenu;
import com.cnframe.model.sys.SysRole;
import com.cnframe.model.sys.SysUser;

public interface RightService {
	public SysUser findSysUserByUserCode(String userCode);
	public List<SysRole> findRoleListByUserCode(String userCode);
	public List<SysMenu> findMenuListByRoleCode(String roleCode) ;
	public boolean isMenuUrl(String menuUrl);
}
