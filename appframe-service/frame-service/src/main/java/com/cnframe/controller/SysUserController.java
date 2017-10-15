package com.cnframe.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnframe.exception.ServiceException;
import com.cnframe.model.sys.SysUser;
import com.cnframe.modules.security.MD5Util;
import com.cnframe.repository.SysUserRepository;
import com.cnframe.repository.SysUserSpec;
@CrossOrigin
@RestController
public class SysUserController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SysUserRepository sysUserRepository;
	
	@RequestMapping("/sysuser/save")
	public ResponseEntity<SysUser> save(RequestEntity <SysUser> requestEntity){
		SysUser sysUser = requestEntity.getBody();
		if (StringUtils.isEmpty(String.valueOf(sysUser.getId())) || String.valueOf(sysUser.getId())=="null"){
			sysUser.setUserPass(MD5Util.MD5(sysUser.getUserPass()));
		}
		sysUser = sysUserRepository.saveAndFlush(sysUser);
		return new ResponseEntity<SysUser>(sysUser, HttpStatus.OK);
	}
	@RequestMapping("/sysuser/findall")
	public ResponseEntity<List<SysUser>> findAll(RequestEntity <SysUser> requestEntity){
		SysUser sysUser = requestEntity.getBody();
		if (sysUser == null){sysUser = new SysUser();}
		SysUserSpec spec = new SysUserSpec(sysUser);
		List<SysUser> sysUserList = sysUserRepository.findAll(spec);
		System.out.println(new ResponseEntity<List<SysUser>>(sysUserList, HttpStatus.OK));
		return new ResponseEntity<List<SysUser>>(sysUserList, HttpStatus.OK);
	}
	@RequestMapping("/sysuser/delete")
	public ResponseEntity<SysUser> delete(RequestEntity <SysUser> requestEntity){
		SysUser sysUser = requestEntity.getBody();
		sysUserRepository.delete(sysUser.getId());
		return new ResponseEntity<SysUser>(sysUser, HttpStatus.OK);
	}
	
	@RequestMapping("/sysuser/login")
	public ResponseEntity<SysUser> login(RequestEntity <SysUser> requestEntity){
		SysUser sysUser = requestEntity.getBody();
		SysUser dbsysUser = sysUserRepository.findByUserCode(sysUser.getUserCode());
		if (dbsysUser == null){
			throw new ServiceException("FrameService","login","用户不存在或密码错误！");
		}
		logger.info(MD5Util.MD5(sysUser.getUserPass()));
		if (!dbsysUser.getUserPass().equals(MD5Util.MD5(sysUser.getUserPass()))){
			throw new ServiceException("FrameService","login","用户不存在或密码错误！");
		}
		return new ResponseEntity<SysUser>(sysUser, HttpStatus.OK);
	}
	@RequestMapping("/sysuser/logout")
	public ResponseEntity<SysUser> logout(RequestEntity <SysUser> requestEntity){
		SysUser sysUser = requestEntity.getBody();
		return new ResponseEntity<SysUser>(sysUser, HttpStatus.OK);
	}
}
