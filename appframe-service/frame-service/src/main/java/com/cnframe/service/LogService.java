package com.cnframe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cnframe.model.sys.SysUserLog;
import com.cnframe.repository.SysUserLogRepository;

@Service("LogService")
public class LogService {
	@Autowired
	public SysUserLogRepository sysUserLogRepository;
	public void LogDebug(String logMain, String logContent,String userCode,String funId){
		LogComm("01",logMain,logContent,userCode,funId);
		
	}
	public void LogInfo(String logMain, String logContent,String userCode,String funId){
		LogComm("02",logMain,logContent,userCode,funId);
	}
	public void LogWarn(String logMain, String logContent,String userCode,String funId){
		LogComm("03",logMain,logContent,userCode,funId);
	}
	public void LogError(String logMain, String logContent,String userCode,String funId){
		LogComm("04",logMain,logContent,userCode,funId);
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)//日志记录起新事务
	public void LogComm(String logLevel,String logMain,String logContent,String userCode,String funId){
		SysUserLog sysUserLog = new SysUserLog();
		sysUserLog.setUserCode(userCode);
		if (userCode==null || "".equals(userCode)){
			sysUserLog.setUserCode("NULL_USER");
		}
		sysUserLog.setFunId(funId);
		sysUserLog.setLogLevel(logLevel);
		sysUserLog.setLogMain(logMain);
		//oracle9i有此bug，对于1000-2000之间保存数据报错，特殊处理
		int length = logContent.length();
		if(length <= 2000 && length >= 1000){   
			StringBuffer sb=new StringBuffer(logContent);
			for(int i = 0; i <= (2000 - length); i++){
				sb.append(" ");
			}
		    logContent = sb.toString();
		}
		sysUserLog.setLogContent(logContent);
		sysUserLogRepository.saveAndFlush(sysUserLog);
	}
}
