package com.cnframe.msg;
import java.util.Date;

public class Fault {
	private String faultCode = "00000";//错误代码
	private int faultLevel = 3;//0 debug 1 INFO 2 WARN 3ERROR(0,1应该不会使用的)
	private String faultMsg;//错误消息
	private String faultDetailMsg;//错误详细消息
	private Date faultDate;//发生错误时间
	private String faultDateStr;
	public Fault(){
		faultDate = new Date();
	}
	public String getFaultCode() {
		return faultCode;
	}
	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}
	public String getFaultMsg() {
		return faultMsg;
	}
	public void setFaultMsg(String faultMsg) {
		this.faultMsg = faultMsg;
	}
	public String getFaultDetailMsg() {
		return faultDetailMsg;
	}
	public void setFaultDetailMsg(String faultDetailMsg) {
		this.faultDetailMsg = faultDetailMsg;
	}
	public Date getFaultDate() {
		return faultDate;
	}
	public void setFaultDate(Date faultDate) {
		this.faultDate = faultDate;
	}
	public String getFaultDateStr(){
		java.text.SimpleDateFormat format =
			new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (faultDate == null){
			Date date = new Date(System.currentTimeMillis());
			faultDateStr = format.format(date);
		}else {
			faultDateStr = format.format(faultDate);
		}
		return faultDateStr;
	}
	public boolean hasFault(){
		if (faultCode==null || "00000".equals(faultCode)){
			return false;
		}else {
			return true;
		}
	}
	public int getFaultLevel() {
		return faultLevel;
	}
	public void setFaultLevel(int faultLevel) {
		this.faultLevel = faultLevel;
	}
}
