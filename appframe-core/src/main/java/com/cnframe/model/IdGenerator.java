package com.cnframe.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * ID生成策略
 * yyyyMMddHHmm+7位长度序列，循环使用
 * @author Administrator
 *
 */
public class IdGenerator implements IdentifierGenerator {
	private int idIndex = 1;
	public Serializable generate(SessionImplementor session,
			   Object obj) throws HibernateException, HibernateException {
		String idValue="";
		DateFormat dfmt = new SimpleDateFormat("yyyyMMddHHmmss");
		idIndex = idIndex+1;
		if (idIndex>=100000){
			idIndex = 1;
		}
		idValue = dfmt.format(new Date()) + String.format("%1$05d", idIndex);
		return Long.valueOf(idValue);
		//return new UUIDHexGenerator().generate(session,obj);
	}
	
	public static void main(String[] args) {
        UUID uuid = UUID.randomUUID(); 
        System.out.println (uuid);
      }
}
