package com.cnframe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnframe.model.photo.PhotoInfoDetail;
import com.cnframe.repository.PhotoInfoDetailRepository;

@CrossOrigin
@RestController
public class PhotoInfoDetailController extends BaseController{
	@Autowired
	private PhotoInfoDetailRepository photoInfoDetailRepository;
	@RequestMapping("/photoinfodetail/findall")
	public ResponseEntity<List<PhotoInfoDetail>> findAll(){
		List<PhotoInfoDetail> photoInfoDetailList = photoInfoDetailRepository.findAll();
		return new ResponseEntity<List<PhotoInfoDetail>>(photoInfoDetailList, HttpStatus.OK);
	}
}
