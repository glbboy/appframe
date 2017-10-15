package com.cnframe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cnframe.model.sys.SysUserLog;

public interface SysUserLogRepository extends BaseRepository<SysUserLog, Long>{
	List<SysUserLog> findByUserCode(String userCode);
	List<SysUserLog> findByLogLevel(String loglevel);
	@Query("Select s From SysUserLog s where s.userCode = ? and s.logLevel=? order by logTime desc")
	List<SysUserLog> findByUserCodeAndLogLevel(String userCode,String logLevel);
}
