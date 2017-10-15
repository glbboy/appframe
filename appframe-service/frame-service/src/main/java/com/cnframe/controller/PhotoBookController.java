package com.cnframe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnframe.model.photo.PhotoBook;
import com.cnframe.repository.PhotoBookRepository;

@CrossOrigin
@RestController
public class PhotoBookController extends BaseController{
	@Autowired
	private PhotoBookRepository photoBookRepository;
	@RequestMapping("/photobook/findall")
	public ResponseEntity<List<PhotoBook>> findAll(){
		List<PhotoBook> photoBookList = photoBookRepository.findAll();
		return new ResponseEntity<List<PhotoBook>>(photoBookList, HttpStatus.OK);
	}
}
