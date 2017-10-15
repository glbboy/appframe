package com.cnframe.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnframe.model.photo.PhotoBook;
import com.cnframe.model.photo.PhotoInfo;
import com.cnframe.repository.PhotoBookImgsRepository;
import com.cnframe.repository.PhotoBookRepository;
import com.cnframe.repository.PhotoInfoRepository;

@CrossOrigin
@RestController
public class PhotoInfoController extends BaseController{
	@Autowired
	private PhotoInfoRepository photoInfoRepository;
	@Autowired
	private PhotoBookRepository photoBookRepository;
	@Autowired
	private PhotoBookImgsRepository photoBookImgsRepository;
	//获取年份列表
	@RequestMapping("/photoinfo/findyears")
	public ResponseEntity<List<PhotoInfo>> findYears(){
		List<PhotoInfo> photoInfoList = photoInfoRepository.findYears();
		for (int i=0;i<photoInfoList.size();i++){
			PhotoInfo photoInfo = photoInfoList.get(i);
			photoInfo.setBookId(Long.valueOf(photoInfo.getYear()));
			photoInfo.setBookName(photoInfo.getYear()+"年相册");
		}
		return new ResponseEntity<List<PhotoInfo>>(photoInfoList, HttpStatus.OK);
	}
	
	@RequestMapping("/photoinfo/findbooks")
	public ResponseEntity<List<PhotoInfo>> findBooks(){
		List<PhotoInfo> photoInfoList = photoInfoRepository.findBooks();
		for (int i=0;i<photoInfoList.size();i++){
			PhotoInfo photoInfo = photoInfoList.get(i);
			PhotoBook photoBook = photoBookRepository.findByImgID(photoInfo.getId());
			photoInfo.setBookId(photoBook.getId());
			photoInfo.setBookName(photoBook.getBookName());
			photoInfo.setCount(photoBook.getCount());
		}
		return new ResponseEntity<List<PhotoInfo>>(photoInfoList, HttpStatus.OK);
	}
	
	@RequestMapping("/photoinfo/findbybook/{bookId}")
	public ResponseEntity<List<PhotoInfo>> findByBookId(@PathVariable Long bookId){
		if (bookId<9999){
			return findYear(Integer.valueOf(bookId.toString()));
		}
		List<PhotoInfo> photoInfoList = photoInfoRepository.findByBookId(bookId);
		return new ResponseEntity<List<PhotoInfo>>(photoInfoList, HttpStatus.OK);
	}
	
	public ResponseEntity<List<PhotoInfo>> findYear(@PathVariable Integer year){
		List<PhotoInfo> photoInfoList = photoInfoRepository.findByYearOrderByShotDateDesc(year);
		return new ResponseEntity<List<PhotoInfo>>(photoInfoList, HttpStatus.OK);
	}
	
	@RequestMapping("/photoinfo/delete")
	public ResponseEntity<PhotoInfo> delete(RequestEntity<PhotoInfo> requestEntity){
		PhotoInfo photoInfo = requestEntity.getBody();
		photoInfo = photoInfoRepository.findOne(photoInfo.getId());
		photoInfo.setInactiveTime(new Date());
		photoInfoRepository.saveAndFlush(photoInfo);
		photoBookImgsRepository.deleteByImgId(photoInfo.getId());
		return new ResponseEntity<PhotoInfo>(photoInfo, HttpStatus.OK);
	}
	
	
}
