package com.cnframe.repository;

import java.util.List;

import com.cnframe.model.sys.SysPrivilege;
import org.springframework.data.repository.CrudRepository;

public interface SysPrivilegeRepository  extends CrudRepository<SysPrivilege, Long>{
	public List<SysPrivilege> findByPrivilegeMaster(String privilegeMaster);
}
