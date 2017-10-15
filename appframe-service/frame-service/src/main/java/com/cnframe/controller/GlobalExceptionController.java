package com.cnframe.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cnframe.exception.ServiceException;
import com.cnframe.model.sys.SysUserLog;
import com.cnframe.repository.SysUserLogRepository;

@ControllerAdvice
@CrossOrigin
@RestController
public class GlobalExceptionController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SysUserLogRepository sysUserLogRepository;//org.springframework.dao.DataIntegrityViolationException
	//com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Map<String,Object>> handServiceException(ServiceException e) {
    	Map<String, Object> errorMap = new HashMap<String, Object>();  
        errorMap.put("faultCode", e.getFaultCode());   
        errorMap.put("faultMsg", e.getFaultMsg());
        errorMap.put("faultDetailMsg", e.getFaultDetailMsg());
        LogError((String)errorMap.get("faultCode"),(String)errorMap.get("faultMsg"),(String)errorMap.get("faultDetailMsg"));
        return new ResponseEntity<Map<String,Object>>(errorMap,HttpStatus.BAD_REQUEST);  
    }
    @ExceptionHandler({SQLException.class,org.hibernate.exception.ConstraintViolationException.class})  
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)  
    public ResponseEntity<Map<String,Object>> handleSQLException(HttpServletRequest request,  SQLException e) {    
    	logger.error(ExceptionUtils.getFullStackTrace(e));  // 记录错误信息
        Map<String, Object> errorMap = new HashMap<String, Object>();  
        errorMap.put("faultCode", HttpStatus.INTERNAL_SERVER_ERROR.toString());  
        errorMap.put("faultMsg", "数据库操作异常");  
        errorMap.put("faultDetailMsg", e.getMessage());  
        LogError((String)errorMap.get("faultCode"),(String)errorMap.get("faultMsg"),(String)errorMap.get("faultDetailMsg"));
        return new ResponseEntity<Map<String,Object>>(errorMap,HttpStatus.INTERNAL_SERVER_ERROR);  
    }  
    /** 
     * 400 - Bad Request 
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)  
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String,Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
    	logger.error(ExceptionUtils.getFullStackTrace(e));
    	Map<String, Object> errorMap = new HashMap<String, Object>();  
        errorMap.put("faultCode", HttpStatus.BAD_REQUEST.toString());   
        errorMap.put("faultMsg", "参数解析失败");
        errorMap.put("faultDetailMsg", e.getMessage());
        LogError((String)errorMap.get("faultCode"),(String)errorMap.get("faultMsg"),(String)errorMap.get("faultDetailMsg"));
        return new ResponseEntity<Map<String,Object>>(errorMap,HttpStatus.BAD_REQUEST);  
    }  
    /** 
     * 405 - Method Not Allowed 
     */  
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)  
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)  
    public ResponseEntity<Map<String,Object>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    	logger.error(ExceptionUtils.getFullStackTrace(e));
    	Map<String, Object> errorMap = new HashMap<String, Object>();  
        errorMap.put("faultCode", HttpStatus.METHOD_NOT_ALLOWED.toString());   
        errorMap.put("faultMsg", "不支持当前请求方法");
        errorMap.put("faultDetailMsg", e.getMessage()); 
        LogError((String)errorMap.get("faultCode"),(String)errorMap.get("faultMsg"),(String)errorMap.get("faultDetailMsg"));
        return new ResponseEntity<Map<String,Object>>(errorMap,HttpStatus.METHOD_NOT_ALLOWED);  
    }  
  
    /** 
     * 415 - Unsupported Media Type 
     */  
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)  
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)  
    public ResponseEntity<Map<String,Object>> handleHttpMediaTypeNotSupportedException(Exception e) { 
    	logger.error(ExceptionUtils.getFullStackTrace(e));
    	Map<String, Object> errorMap = new HashMap<String, Object>();  
        errorMap.put("faultCode", HttpStatus.UNSUPPORTED_MEDIA_TYPE.toString());   
        errorMap.put("faultMsg", "不支持当前媒体类型");
        errorMap.put("faultDetailMsg", e.getMessage());
        LogError((String)errorMap.get("faultCode"),(String)errorMap.get("faultMsg"),(String)errorMap.get("faultDetailMsg"));
        return new ResponseEntity<Map<String,Object>>(errorMap,HttpStatus.UNSUPPORTED_MEDIA_TYPE);  
    }  
  
    /** 
     * 500 - Internal Server Error 
     */  
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  
    @ExceptionHandler(Exception.class)  
    public ResponseEntity<Map<String,Object>> handleException(Exception e) {  
    	logger.error(ExceptionUtils.getFullStackTrace(e));
    	Map<String, Object> errorMap = new HashMap<String, Object>();  
        errorMap.put("faultCode", HttpStatus.INTERNAL_SERVER_ERROR.toString());   
        errorMap.put("faultMsg", "服务运行异常");
        errorMap.put("faultDetailMsg", e.getMessage());
        LogError((String)errorMap.get("faultCode"),(String)errorMap.get("faultMsg"),(String)errorMap.get("faultDetailMsg"));
        return new ResponseEntity<Map<String,Object>>(errorMap,HttpStatus.INTERNAL_SERVER_ERROR);  
    } 
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void LogError(String funId,String logMain,String logContent){
    	SysUserLog sysUserLog = new SysUserLog();
    	sysUserLog.setLogLevel("04");
    	sysUserLog.setUserCode("globalError");
    	sysUserLog.setFunId(funId);
    	sysUserLog.setLogMain(logMain);
    	if (logContent.length()>4000){
    		sysUserLog.setLogContent(logContent.substring(0, 4000));
    	}else {
    		sysUserLog.setLogContent(logContent);
    	}
    	sysUserLog.setLogTime(new Date());
    	sysUserLogRepository.saveAndFlush(sysUserLog);
    }
//    @ExceptionHandler(BadRequestException.class)  
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)  
//    public ModelAndView handleBadRequestException(  HttpServletRequest request, BadRequestException ex) {  
//        handleLog(request, ex);  
//        ExceptionModel exceptionModel = getExceptionModel(  
//                HttpStatus.BAD_REQUEST, ex);  
//        return new ModelAndView(JSONUtils.VIEW_NAME,  
//                JSONStringView.JSON_MODEL_DATA, exceptionModel);  
//    }  
//  
//    @ExceptionHandler(ServerRejectException.class)  
//    @ResponseStatus(value = HttpStatus.FORBIDDEN)  
//    public ModelAndView handleServerRejectException(  
//            HttpServletRequest request, ServerRejectException ex) {  
//        handleLog(request, ex);  
//        ExceptionModel exceptionModel = getExceptionModel(HttpStatus.FORBIDDEN,  
//                ex);  
//        return new ModelAndView(JSONUtils.VIEW_NAME,  
//                JSONStringView.JSON_MODEL_DATA, exceptionModel);  
//    }  
//  
//    @ExceptionHandler(NotFoundException.class)  
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)  
//    public ModelAndView handleNotFoundException(HttpServletRequest request,  
//            NotFoundException ex) {  
//        handleLog(request, ex);  
//        ExceptionModel exceptionModel = getExceptionModel(HttpStatus.NOT_FOUND,  
//                ex);  
//        return new ModelAndView(JSONUtils.VIEW_NAME,  
//                JSONStringView.JSON_MODEL_DATA, exceptionModel);  
//    }  
//  
//    @ExceptionHandler(SystemException.class)  
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)  
//    public ModelAndView handleSystemException(HttpServletRequest request,  
//            SystemException ex) {  
//        handleLog(request, ex);  
//        ExceptionModel exceptionModel = getExceptionModel(  
//                HttpStatus.INTERNAL_SERVER_ERROR, ex);  
//        return new ModelAndView(JSONUtils.VIEW_NAME,  
//                JSONStringView.JSON_MODEL_DATA, exceptionModel);  
//    }  
//  
//    @ExceptionHandler(Exception.class)  
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)  
//    public ModelAndView handleAllException(HttpServletRequest request,  
//            Exception ex) {  
//  
//        handleLog(request, ex);  
//        Map<String, Object> errorMap = new HashMap<String, Object>();  
//        errorMap.put("code",  
//                Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));  
//        errorMap.put("message", ex.toString());  
//        return new ModelAndView(JSONUtils.VIEW_NAME,  
//                JSONStringView.JSON_MODEL_DATA, errorMap);  
//  
//    }  
//  
//    private ExceptionModel getExceptionModel(HttpStatus httpStatus,  
//            CommonException ex) {  
//        ExceptionModel exceptionModel = new ExceptionModel();  
//        ErrorENUM errorEnum = ex.getErrorEnum();  
//        exceptionModel.setStatus(httpStatus.value());  
//        exceptionModel.setMoreInfo(ex.getMoreInfo());  
//        if (errorEnum != null) {  
//            exceptionModel.setErrorCode(errorEnum.getCode());  
//            exceptionModel.setMessage(errorEnum.toString());  
//        }  
//        return exceptionModel;  
//    }  
//  
//    private void handleLog(HttpServletRequest request, Exception ex) {  
//        Map parameter = request.getParameterMap();  
//        StringBuffer logBuffer = new StringBuffer();  
//        if (request != null) {  
//            logBuffer.append("  request method=" + request.getMethod());  
//            logBuffer.append("  url=" + request.getRequestURL());  
//        }  
//        if (ex instanceof CommonException) {  
//            logBuffer.append("  moreInfo="  
//                    + ((CommonException) ex).getMoreInfo());  
//        }  
//        if (ex != null) {  
//            logBuffer.append("  exception:" + ex);  
//        }  
//        logger.error(logBuffer.toString());  
//    }  
}
