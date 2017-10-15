package com.cnframe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnframe.model.chg.ChgAccountBookRel;
import com.cnframe.repository.ChgAccountBookRelRepository;

@CrossOrigin
@RestController
public class ChgAccountBookRelController extends BaseController{
	@Autowired
	private ChgAccountBookRelRepository chgAccountBookRepository;
	@RequestMapping("/chgaccountbookrel/findall")
	public ResponseEntity<List<ChgAccountBookRel>> findAll(){
		List<ChgAccountBookRel> chgAccoutBookRelList = chgAccountBookRepository.findAllOrderByAccountBookAndSort();
		return new ResponseEntity<List<ChgAccountBookRel>>(chgAccoutBookRelList, HttpStatus.OK);
	}
}
