package com.cnframe.repository;

import com.cnframe.model.sys.SysUser;

public interface SysUserRepository  extends BaseRepository<SysUser, Long>{
	public SysUser findByUserCode(String userCode);
	public SysUser findById(Long id);
	public void deleteById(String id);
}
