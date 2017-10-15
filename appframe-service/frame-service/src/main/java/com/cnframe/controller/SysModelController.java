package com.cnframe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnframe.model.sys.SysModel;
import com.cnframe.repository.SysModelRepository;

@CrossOrigin
@RestController
public class SysModelController {
	@Autowired
	private SysModelRepository sysModelRepository;
	@RequestMapping("/sysmodel/{model}")
	public ResponseEntity<List<?>> getModel(@PathVariable String model){
		System.out.println("model:"+model);
		List<SysModel> sysModelList = sysModelRepository.findByModelOrderBySort(model);
		return new ResponseEntity<List<?>>(sysModelList, HttpStatus.OK);
	}
}
