package com.cnframe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnframe.model.sys.SysUser;
import com.cnframe.repository.SysUserRepository;
import com.cnframe.service.RightService;

@RestController
public class RightController {
	@Autowired
	public RightService rightService;
	
	@Autowired
	private SysUserRepository  sysUserRepository;
	
	@RequestMapping("/sys/sysuser/{userCode}")
    public SysUser findSysUserByUserCode(@PathVariable String userCode) {
        return rightService.findSysUserByUserCode(userCode);
    }
	@RequestMapping("/sys/sysuserlist")
	public Iterable<?> findAllUser() {
		return sysUserRepository.findAll();
	}
}
