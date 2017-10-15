package com.cnframe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnframe.model.chg.ChgAccountBook;
import com.cnframe.repository.ChgAccountBookRepository;

@CrossOrigin
@RestController
public class ChgAccountBookController extends BaseController{
	@Autowired
	private ChgAccountBookRepository chgAccountBookRepository;
	@RequestMapping("/chgaccountbook/findall")
	public ResponseEntity<List<ChgAccountBook>> findAll(){
		List<ChgAccountBook> chgAccoutBookList = chgAccountBookRepository.findAll();
		return new ResponseEntity<List<ChgAccountBook>>(chgAccoutBookList, HttpStatus.OK);
	}
}
