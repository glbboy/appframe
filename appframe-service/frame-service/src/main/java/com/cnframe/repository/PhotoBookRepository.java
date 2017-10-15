package com.cnframe.repository;

import org.springframework.data.jpa.repository.Query;

import com.cnframe.model.photo.PhotoBook;


public interface PhotoBookRepository extends BaseRepository<PhotoBook, Long>{
	public PhotoBook findByBookName(String bookName);
	@Query("Select s From PhotoBook s,PhotoBookImgs st where st.bookId = s.id and st.imgId= ? ")
	public PhotoBook findByImgID(Long imgId);
}
