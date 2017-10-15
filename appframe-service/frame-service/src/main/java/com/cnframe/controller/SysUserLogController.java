package com.cnframe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnframe.model.sys.SysUserLog;
import com.cnframe.repository.SysUserLogRepository;
@CrossOrigin
@RestController
public class SysUserLogController {
	@Autowired
	private SysUserLogRepository sysUserLogRepository;

	@RequestMapping("/sysuserlog/findall")
	public ResponseEntity<List<SysUserLog>> findAll(){
		List<SysUserLog> sysUserLoglList = sysUserLogRepository.findAll();
		return new ResponseEntity<List<SysUserLog>>(sysUserLoglList, HttpStatus.OK);
	}
	@RequestMapping("/sysuserlog/findByUserCode/{userCode}")
	public ResponseEntity<List<SysUserLog>> findByUserCode(@PathVariable String userCode){
		List<SysUserLog> sysUserLoglList = sysUserLogRepository.findByUserCode(userCode);
		return new ResponseEntity<List<SysUserLog>>(sysUserLoglList, HttpStatus.OK);
	}
	@RequestMapping("/sysuserlog/findByLogLevel/{logLevel}")
	public ResponseEntity<List<SysUserLog>> findByLogLevel(@PathVariable String logLevel){
		List<SysUserLog> sysUserLoglList = sysUserLogRepository.findByLogLevel(logLevel);
		return new ResponseEntity<List<SysUserLog>>(sysUserLoglList, HttpStatus.OK);
	}
	@RequestMapping("/sysuserlog/findByUserCodeAndLogLevel/{userCode}/{logLevel}")
	public ResponseEntity<List<SysUserLog>> findByUserCodeAndLogLevel(@PathVariable String userCode,String logLevel){
		List<SysUserLog> sysUserLoglList = sysUserLogRepository.findByUserCodeAndLogLevel(userCode,logLevel);
		return new ResponseEntity<List<SysUserLog>>(sysUserLoglList, HttpStatus.OK);
	}
	
}
