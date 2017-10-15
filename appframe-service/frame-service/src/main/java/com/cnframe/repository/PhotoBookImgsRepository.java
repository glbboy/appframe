package com.cnframe.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cnframe.model.photo.PhotoBookImgs;

public interface PhotoBookImgsRepository extends BaseRepository<PhotoBookImgs, Long>{
	public List<PhotoBookImgs> findByBookIdOrderByCreateTimeDesc(Long bookId);
	@Modifying
	@Transactional
	@Query(value="delete from PhotoBookImgs c where c.imgId = ?1")
	public void deleteByImgId(Long imgId);
	
	public PhotoBookImgs findByImgId(Long imgId);
}
