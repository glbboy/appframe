package com.cnframe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnframe.model.sys.SysDict;
import com.cnframe.model.sys.SysDictDetail;
import com.cnframe.repository.SysDictDetailRepository;
import com.cnframe.repository.SysDictRepository;
@CrossOrigin
@RestController
public class SysDictDetailController {
	@Autowired
	private SysDictDetailRepository sysDictDetailRepository;
	@Autowired
	private SysDictRepository sysDictRepository;
	
	
	@RequestMapping("/sysdictdetail/save")
	public ResponseEntity<SysDictDetail> save(RequestEntity <SysDictDetail> requestEntity){
		SysDictDetail sysDictDetail = requestEntity.getBody();
		sysDictDetail = sysDictDetailRepository.saveAndFlush(sysDictDetail);
		return new ResponseEntity<SysDictDetail>(sysDictDetail, HttpStatus.OK);
	}
	@RequestMapping("/sysdictdetail/findall")
	public ResponseEntity<List<SysDictDetail>> findAll(){
		List<SysDictDetail> sysDictDetailList = sysDictDetailRepository.findByOrderByDictCodeSort();
		return new ResponseEntity<List<SysDictDetail>>(sysDictDetailList, HttpStatus.OK);
	}
	@RequestMapping("/sysdictdetail/findGroupByDictFlag")
	public ResponseEntity<List<SysDictDetail>> findGroupByDictFlag(){
		List<SysDictDetail> sysDictDetailList = sysDictDetailRepository.findGroupByDictFlag();
		return new ResponseEntity<List<SysDictDetail>>(sysDictDetailList, HttpStatus.OK);
	}
	
	@RequestMapping("/sysdictdetail/find/{dictCode}")
	public ResponseEntity<List<SysDictDetail>> findByDictCodeOrderBySort(@PathVariable String dictCode){
		List<SysDictDetail> sysDictDetailList = sysDictDetailRepository.findByDictCodeOrderBySort(dictCode);
		return new ResponseEntity<List<SysDictDetail>>(sysDictDetailList, HttpStatus.OK);
	}
	@RequestMapping("/sysdictdetail/delete")
	public ResponseEntity<SysDictDetail> delete(RequestEntity <SysDictDetail> requestEntity){
		SysDictDetail sysDictDetail = requestEntity.getBody();
		sysDictDetailRepository.delete(sysDictDetail.getId());
		return new ResponseEntity<SysDictDetail>(sysDictDetail, HttpStatus.OK);
	}
	@RequestMapping("/sysdict/findAll")
	public ResponseEntity<List<SysDict>> findDictAll(){
		List<SysDict> sysDictList = sysDictRepository.findByOrderBySort();
		return new ResponseEntity<List<SysDict>>(sysDictList, HttpStatus.OK);
	}
	
	
}
