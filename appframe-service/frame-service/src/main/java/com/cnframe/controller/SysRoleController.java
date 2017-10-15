package com.cnframe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnframe.model.sys.SysRole;
import com.cnframe.repository.SysRoleRepository;
@CrossOrigin
@RestController
public class SysRoleController {
	@Autowired
	private SysRoleRepository sysRoleRepository;
	
	@RequestMapping("/sysrole/save")
	public ResponseEntity<SysRole> save(RequestEntity <SysRole> requestEntity){
		SysRole sysRole = requestEntity.getBody();
		sysRole = sysRoleRepository.saveAndFlush(sysRole);
		return new ResponseEntity<SysRole>(sysRole, HttpStatus.OK);
	}
	@RequestMapping("/sysrole/findall")
	public ResponseEntity<List<SysRole>> findAll(){
		List<SysRole> sysRoleList = sysRoleRepository.findAll();
		System.out.println(new ResponseEntity<List<SysRole>>(sysRoleList, HttpStatus.OK));
		return new ResponseEntity<List<SysRole>>(sysRoleList, HttpStatus.OK);
	}
	@RequestMapping("/sysrole/delete")
	public ResponseEntity<SysRole> delete(RequestEntity <SysRole> requestEntity){
		SysRole sysRole = requestEntity.getBody();
		sysRoleRepository.delete(sysRole);
		return new ResponseEntity<SysRole>(sysRole, HttpStatus.OK);
	}
	
}
