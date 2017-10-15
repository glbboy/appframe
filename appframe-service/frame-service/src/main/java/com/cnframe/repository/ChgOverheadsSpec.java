package com.cnframe.repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.cnframe.model.chg.ChgOverheads;
import com.cnframe.modules.utils.DateUtil;

public class ChgOverheadsSpec implements Specification<ChgOverheads> {
	private final ChgOverheads example;

	public ChgOverheadsSpec(ChgOverheads example) {
		this.example = example;
	}

	@Override
	public Predicate toPredicate(Root<ChgOverheads> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(createOperateTime("accountDate",cb,example.getYear(),root));
		
		if (example.getAccountBook()!=null && StringUtils.isNotBlank(example.getAccountBook().toString())) {
			predicates.add(cb.equal(root.get("accountBook").as(Long.class), example.getAccountBook()));
		}
		if (example.getAccountId()!=null && StringUtils.isNotBlank(example.getAccountId().toString())) {
			predicates.add(cb.equal(root.get("accountId").as(Long.class), example.getAccountId()));
		}
		if (StringUtils.isNotBlank(example.getItemFlag())) {
			predicates.add(cb.equal(root.get("itemFlag").as(String.class), example.getItemFlag()));
		}
		if (StringUtils.isNotBlank(example.getAccountItem())) {
			predicates.add(cb.equal(root.get("accountItem").as(String.class), example.getAccountItem()));
		}
		
		//添加排序的功能  
        query.orderBy(cb.desc(root.get("accountDate").as(Date.class))); 
		Predicate[] predicate = new Predicate[predicates.size()];
		query.where(cb.and(predicates.toArray(predicate)));
		return query.getRestriction();
	}
	
	private Predicate createOperateTime(String key,CriteriaBuilder cb, String value, Root<ChgOverheads> entity) {    
		System.out.println(value);
        Date t1,t2;
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式一定要写正确
        try {
        	t1=df.parse(DateUtil.getDate(-3) + " 00:00:00"); 
			t2=df.parse(DateUtil.getDate(3) + " 00:00:00");//由于可能记录以后的记录，所以多查询3个月数据
			if(StringUtils.isNotBlank(value)){  
	            if (value.length()==4){
	            	t1=df.parse(value+"-01-01" + " 00:00:00");  
	            	t2=df.parse(value+"-12-31" + " 23:59:59"); 
	            }else if (value.length()==5){
	            	t1=df.parse("2000-01-01" + " 00:00:00");  
	            	t2=df.parse(value+"12-31" + " 23:59:59");
	            }
	            
	        }
			return cb.between(entity.<Date> get(key), t1, t2);
        } catch ( Exception e ) {  
	           e.printStackTrace();  
	    }
        return null;  
          
    }  

}
