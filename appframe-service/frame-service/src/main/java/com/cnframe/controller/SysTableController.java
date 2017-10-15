package com.cnframe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnframe.model.sys.SysTable;
import com.cnframe.repository.SysTableRepository;
@CrossOrigin
@RestController
public class SysTableController {
	@Autowired
	private SysTableRepository sysTableRepository;
	
	@RequestMapping("/systable/findall")
	public ResponseEntity<List<SysTable>> findAll(){
		List<SysTable> sysTableList = sysTableRepository.findAll();
		System.out.println(new ResponseEntity<List<SysTable>>(sysTableList, HttpStatus.OK));
		return new ResponseEntity<List<SysTable>>(sysTableList, HttpStatus.OK);
	}
	
}
