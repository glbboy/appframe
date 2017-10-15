package com.cnframe.modules.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DateJsonDeserializer extends JsonDeserializer<Date>{
	public static final SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat shortFormat=new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		try {
			if (p.getTextLength() == 10){
				return shortFormat.parse(p.getText());
			}else {
				return format.parse(p.getText());
			}
		} catch (ParseException e) {
			System.out.println(e.getMessage()); 
			e.printStackTrace();
			throw new RuntimeException(e); 
		}
	}

}
