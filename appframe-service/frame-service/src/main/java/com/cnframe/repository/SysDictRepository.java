package com.cnframe.repository;


import java.util.List;

import com.cnframe.model.sys.SysDict;

public interface SysDictRepository  extends BaseRepository<SysDict, Long>{
	public List<SysDict> findByOrderBySort();
}
