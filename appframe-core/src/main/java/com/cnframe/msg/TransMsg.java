package com.cnframe.msg;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class TransMsg {
	private Head head;//head消息
	private Body body;//body 消息
	private Fault fault;//错误消息
	public TransMsg(){
		this.head = new Head();
		this.body = new Body();
		this.fault = new Fault();
	}
	public TransMsg(String jsonStr){
		
	}
	public TransMsg(Map<String, Object> requestEntity){
		JSONObject json = new JSONObject(requestEntity);
		JSONObject jsonHead = json.getJSONObject("head");
		if (jsonHead != null){
			head = JSONObject.toJavaObject(jsonHead, Head.class);
		}
		JSONObject jsonBody = json.getJSONObject("head");
		if (jsonBody != null){
			this.body = JSONObject.toJavaObject(jsonBody, Body.class);
		}
		JSONObject jsonFault = json.getJSONObject("fault");
		if (fault != null){
			fault = JSONObject.toJavaObject(jsonFault, Fault.class);
		}
	}
	public Head getHead() {
		return head;
	}
	public void setHead(Head head) {
		this.head = head;
	}
	public Body getBody() {
		return body;
	}
	public void setBody(Body body) {
		this.body = body;
	}
	public Fault getFault() {
		return fault;
	}
	public void setFault(Fault fault) {
		this.fault = fault;
	}
	
}
