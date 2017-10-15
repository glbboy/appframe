package com.cnframe.modules.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelUtil {
	private static final Logger logger = LoggerFactory.getLogger(ModelUtil.class);
	private static ModelUtil instance = null;
	private static HashMap<String,HashMap<String,Field>> modelList = new HashMap<String,HashMap<String,Field>>();
	private static DateFormat df8 = new SimpleDateFormat ("yyyyMMdd");
	private static DateFormat df14 = new SimpleDateFormat ("yyyyMMddHHmmss"); 
	private ModelUtil(){
		logger.info("ModelUtil单例私有方法创建！");
	}
	/**
     * 获取类的全部字段
     * @param className
     * @return
     */
    public HashMap<String,Field> getFields(String className){
    	HashMap<String,Field> fFields = new HashMap<String,Field>();
    	Class<?> clazz;
		try {
			clazz = Class.forName(className);
			Field[] fields=clazz.getDeclaredFields();
			while (fields.length>0){
				for(int i=0;i<fields.length;i++){
					String name = fields[i].getName();
					if ("serialVersionUID".equals(name)){
						continue;
					}
					fFields.put(name, fields[i]);
				}
				clazz = clazz.getSuperclass();
				fields = clazz.getDeclaredFields();
			}
		} catch (ClassNotFoundException e) {
		}
    	return fFields;
    }
	private static synchronized void init() {
		instance = new ModelUtil();
	}
	public static ModelUtil getInstance(){
		if (instance == null){
			init();
		}
		return instance;
	}
	
    public void setter(String className,Object obj,String att,Object value){
    	HashMap<String,Field> model = modelList.get(className);
    	if (model == null ){
    		model = getFields(className);
    		modelList.put(className, model);
    	}
    	Field field = model.get(att);
    	if (field == null){
    		//System.out.println(att);
    		return;
    	}
    	String firstLetter = att.substring(0, 1).toUpperCase();
        try {
			Method method = obj.getClass().getMethod("set" + firstLetter+att.substring(1), field.getType());
			if (value == null){
				return;
			}
			String val = (String)value;
			if (field.getType() == Long.class){
				if (StringUtils.isNotBlank(val)){
					method.invoke(obj, Long.valueOf(val)); 
				}
            }else if (field.getType() == long.class){
            	if (StringUtils.isNotBlank(val)){
					method.invoke(obj, Long.valueOf(val).longValue()); 
				}
            }else if (field.getType() == Integer.class){
            	if (StringUtils.isNotBlank(val)){
					method.invoke(obj, Integer.valueOf(val)); 
				}
            }else if (field.getType() == int.class){
            	if (StringUtils.isNotBlank(val)){
					method.invoke(obj, Integer.valueOf(val).intValue()); 
				}
            }else if (field.getType() == Double.class || field.getType() == Float.class){
            	if (StringUtils.isNotBlank(val)){
					method.invoke(obj, Double.valueOf(val)); 
				}
            }else if (field.getType() == Date.class) {
            	if (StringUtils.isNotBlank(val)){
            		if (val.length()==8){
            			method.invoke(obj,df8.parse(val));
            		}else if (val.length()==14){
            			method.invoke(obj,df14.parse(val));
            		}
            	}
			}else if (field.getType() == Boolean.class) {
				if (StringUtils.isNotBlank(val)){
					method.invoke(obj, Boolean.valueOf(Boolean.getBoolean(val))); 
				}
			}else if (field.getType() == String.class) {
				if (StringUtils.isNotBlank(val)){
					method.invoke(obj, val); 
				}
			}else {
            	method.invoke(obj, val); 
            }
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public final static void main(String[] args) throws Exception {
    	
    }
}
