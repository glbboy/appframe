package com.cnframe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cnframe.model.chg.ChgAccountBookRel;


public interface ChgAccountBookRelRepository extends BaseRepository<ChgAccountBookRel, Long>{
	@Query("Select s From ChgAccountBookRel s order by accountBook,sort")
	public List<ChgAccountBookRel> findAllOrderByAccountBookAndSort();
}
