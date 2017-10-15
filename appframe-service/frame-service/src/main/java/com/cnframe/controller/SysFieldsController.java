package com.cnframe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnframe.model.sys.SysField;
import com.cnframe.repository.SysFieldsRepository;
@CrossOrigin
@RestController
public class SysFieldsController {
	@Autowired
	private SysFieldsRepository sysFieldsRepository;
	
	@RequestMapping("/sysfield/findall")
	public ResponseEntity<List<SysField>> findAll(){
		List<SysField> sysFieldList = sysFieldsRepository.findAllOrderByTableIdSort();
		System.out.println(new ResponseEntity<List<SysField>>(sysFieldList, HttpStatus.OK));
		return new ResponseEntity<List<SysField>>(sysFieldList, HttpStatus.OK);
	}
	
}
