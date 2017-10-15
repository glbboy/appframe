package com.cnframe.exception;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String serviceName;
	private String faultCode;
	private String faultMsg;
	private String faultDetailMsg;

	public ServiceException(String serviceName, String faultCode, String faultMsg) {
		this.serviceName = serviceName;
		this.faultCode = faultCode;
		this.faultMsg = faultMsg;
		this.faultDetailMsg = faultMsg;
	}
	public ServiceException(String serviceName, String faultCode, String faultMsg,String faultDetailMsg) {
		this.serviceName = serviceName;
		this.faultCode = faultCode;
		this.faultMsg = faultMsg;
		this.faultDetailMsg = faultDetailMsg;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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
}
