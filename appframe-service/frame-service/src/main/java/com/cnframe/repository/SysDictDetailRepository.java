package com.cnframe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cnframe.model.sys.SysDictDetail;

public interface SysDictDetailRepository  extends BaseRepository<SysDictDetail, Long>{
	List<SysDictDetail> findByDictCodeOrderBySort(String dictCode);
	@Query("Select s From SysDictDetail s order by dictCode,sort")
	List<SysDictDetail> findByOrderByDictCodeSort();
	@Query("Select s From SysDictDetail s where dictflag is not null group by dictcode,dictflag")
	List<SysDictDetail> findGroupByDictFlag();
}
