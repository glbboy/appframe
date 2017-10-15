package com.cnframe.repository;

import com.cnframe.model.sys.SysRole;

public interface SysRoleRepository  extends BaseRepository<SysRole, Long>{
	public SysRole findByRoleCode(String roleCode);
}
