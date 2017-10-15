package com.cnframe.repository;

import com.cnframe.model.photo.PhotoBook;


public interface PhotoBookRepository extends BaseRepository<PhotoBook, Long>{
	public PhotoBook findByBookName(String bookName);
}
