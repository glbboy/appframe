package com.cnframe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cnframe.model.sys.SysField;

public interface SysFieldsRepository  extends BaseRepository<SysField, Long>{
	@Query("Select s From SysField s order by tableId,sort")
	public List<SysField> findAllOrderByTableIdSort();
}
