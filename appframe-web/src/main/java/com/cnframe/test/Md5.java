package com.cnframe.test;

import java.math.BigInteger;
import java.security.MessageDigest;


public class Md5 {

	public static void main(String[] args) throws Exception{
		System.out.println(getMD5("gelb").toUpperCase());
	}
	public static String getMD5(String str) throws Exception {
		
	    try {
	        // 生成一个MD5加密计算摘要
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        // 计算md5函数
	        md.update(str.getBytes());
	        return new BigInteger(1, md.digest()).toString(16);
	    } catch (Exception e) {
	       throw new Exception("MD5加密出错："+e.getMessage());
	    }
	}
}
