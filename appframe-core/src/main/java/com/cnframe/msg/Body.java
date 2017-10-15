package com.cnframe.msg;

import java.util.HashMap;
import java.util.Map;

public class Body {
	private Map<String,Object> inPara;//
	private Map<String,Object> outPara;//返回参数
	public Body(){
		inPara = new HashMap<String, Object>();
		outPara = new HashMap<String, Object>();
	}
	public Map<String, Object> getInPara() {
		return inPara;
	}
	public void setInPara(Map<String, Object> inPara) {
		this.inPara = inPara;
	}
	public Map<String, Object> getOutPara() {
		return outPara;
	}
	public void setOutPara(Map<String, Object> outPara) {
		this.outPara = outPara;
	}
	
}
