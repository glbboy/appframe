package com.cnframe.service.impl;

import java.util.List;

import com.cnframe.model.sys.SysMenu;
import com.cnframe.model.sys.SysRole;
import com.cnframe.model.sys.SysUser;
import com.cnframe.repository.SysPrivilegeRepositoryCustom;
import com.cnframe.repository.SysUserRepository;
import com.cnframe.service.RightService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service(value="rightService")
public class RightServiceImpl implements RightService {
	@Autowired
	private SysUserRepository  sysUserRepository;
	@Autowired
	private SysPrivilegeRepositoryCustom sysPrivilegeRepositoryCustom;
	//@Autowired
	//private RedisService redisService;
	public SysUser findSysUserByUserCode(String userCode) {
		Assert.notNull(userCode,"用户编码不能为空！");
		return sysUserRepository.findByUserCode(userCode);
	}
	/**
	 * 根据用户编码获取角色列表
	 */
	public List<SysRole> findRoleListByUserCode(String userCode){
		return sysPrivilegeRepositoryCustom.findRoleListByUserCode(userCode);
	}
	/**
	 * 根据角色编码获取功能权限列表
	 */
	public List<SysMenu> findMenuListByRoleCode(String roleCode) {
		return sysPrivilegeRepositoryCustom.findMenuListByRoleCode(roleCode);
	}
	/**
	 * 判断menuUrl是否菜单或功能点
	 */
	public boolean isMenuUrl(String menuUrl){
		SysMenu sysMenu = new SysMenu();
		sysMenu.setMenuUrl(menuUrl);
		//sysMenu = redisService.getMenuUrl(sysMenu);
		if (sysMenu == null){
			return false;
		}
		return true;
	}
}
