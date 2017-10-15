package com.cnframe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnframe.model.photo.PhotoBookImgs;
import com.cnframe.repository.PhotoBookImgsRepository;

@CrossOrigin
@RestController
public class PhotoBookImgsController extends BaseController{
	@Autowired
	private PhotoBookImgsRepository photoBookImgsRepository;
	@RequestMapping("/photobookimgs/findall")
	public ResponseEntity<List<PhotoBookImgs>> findAll(){
		List<PhotoBookImgs> photoBookImgsList = photoBookImgsRepository.findAll();
		return new ResponseEntity<List<PhotoBookImgs>>(photoBookImgsList, HttpStatus.OK);
	}
	@RequestMapping("/photobookimgs/save")
	public ResponseEntity<PhotoBookImgs> save(RequestEntity <PhotoBookImgs> requestEntity){
		PhotoBookImgs photoBookImgs = requestEntity.getBody();
		Long bookId = photoBookImgs.getBookId();
		logger.info("bookId"+bookId);
		PhotoBookImgs dbPhotoBookImgs = photoBookImgsRepository.findByImgId(photoBookImgs.getImgId());
		System.out.println(dbPhotoBookImgs);
		if (dbPhotoBookImgs == null){
			photoBookImgs = photoBookImgsRepository.saveAndFlush(photoBookImgs);
		}else {
			logger.info("dbPhotoBookImgs"+dbPhotoBookImgs.getBookId());
			logger.info("photoBookImgs"+photoBookImgs.getBookId());
			dbPhotoBookImgs.setBookId(bookId);
			photoBookImgs = photoBookImgsRepository.saveAndFlush(dbPhotoBookImgs);
		}
		return new ResponseEntity<PhotoBookImgs>(photoBookImgs, HttpStatus.OK);
	}
}
