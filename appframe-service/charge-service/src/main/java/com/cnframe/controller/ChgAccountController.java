package com.cnframe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnframe.model.chg.ChgAccount;
import com.cnframe.repository.ChgAccountRepository;

@CrossOrigin
@RestController
public class ChgAccountController extends BaseController{
	@Autowired
	private ChgAccountRepository chgAccountRepository;
	@RequestMapping("/chgaccount/findall")
	public ResponseEntity<List<ChgAccount>> findAll(){
		List<ChgAccount> chgAccoutList = chgAccountRepository.findAll();
		return new ResponseEntity<List<ChgAccount>>(chgAccoutList, HttpStatus.OK);
	}
}
