package com.cnframe.repository;

import java.util.List;
import com.cnframe.model.sys.SysModel;

public interface SysModelRepository extends BaseRepository<SysModel, Long>{
	public List<SysModel> findByModelOrderBySort(String model);
}
