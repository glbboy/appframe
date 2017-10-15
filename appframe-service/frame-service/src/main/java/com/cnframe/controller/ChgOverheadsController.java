package com.cnframe.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnframe.model.chg.ChgOverheads;
import com.cnframe.repository.ChgOverheadsRepository;
import com.cnframe.repository.ChgOverheadsSpec;

@CrossOrigin
@RestController
public class ChgOverheadsController extends BaseController{
	@Autowired
	private ChgOverheadsRepository chgOverheadsRepository;
	@RequestMapping("/chgoverheads/findall")
	public ResponseEntity<List<ChgOverheads>> findAll(RequestEntity<ChgOverheads> requestEntity){
		ChgOverheads chgOverheads = requestEntity.getBody();
		if (chgOverheads == null){chgOverheads = new ChgOverheads();};
		ChgOverheadsSpec spec = new ChgOverheadsSpec(chgOverheads);
		List<ChgOverheads> chgOverheadsList = chgOverheadsRepository.findAll(spec);
		return new ResponseEntity<List<ChgOverheads>>(chgOverheadsList, HttpStatus.OK);
	}
	@RequestMapping("/chgoverheads/save")
	public ResponseEntity<ChgOverheads> save(RequestEntity <ChgOverheads> requestEntity){
		ChgOverheads chgOverheads = requestEntity.getBody();
		chgOverheads = chgOverheadsRepository.saveAndFlush(chgOverheads);
		return new ResponseEntity<ChgOverheads>(chgOverheads, HttpStatus.OK);
	}
	@RequestMapping("/chgoverheads/delete")
	public ResponseEntity<ChgOverheads> delete(RequestEntity <ChgOverheads> requestEntity){
		System.out.println(requestEntity);
		ChgOverheads chgOverheads = requestEntity.getBody();
		System.out.println(chgOverheads);
		chgOverheadsRepository.delete(chgOverheads.getId());
		return new ResponseEntity<ChgOverheads>(chgOverheads, HttpStatus.OK);
	}
	@RequestMapping("/chgoverheads/querymonth")
	public ResponseEntity<List<ChgOverheads>> querymonth(RequestEntity <ChgOverheads> requestEntity){
		return query(requestEntity,"year(accountDate)*100+month(accountDate) as accountDate");
	}
	@RequestMapping("/chgoverheads/queryyear")
	public ResponseEntity<List<ChgOverheads>> queryyear(RequestEntity <ChgOverheads> requestEntity){
		return query(requestEntity,"year(accountDate) as accountDate");
	}
	public ResponseEntity<List<ChgOverheads>> query(RequestEntity <ChgOverheads> requestEntity,String queryStr){
		ChgOverheads requestObj = requestEntity.getBody();
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("Select sum(money) as money,accountBook,accountId,itemFlag,accountItem, ");
		sqlStr.append(queryStr);
		sqlStr.append(" From chg_overheads where 1=1 ");
		if (requestObj !=null && requestObj.getAccountBook() !=null && StringUtils.isNotBlank(requestObj.getAccountBook().toString()) ){
			sqlStr.append(" and accountBook="+requestObj.getAccountBook()+" ");
		}
		if (requestObj !=null && requestObj.getAccountId() !=null && StringUtils.isNotBlank(requestObj.getAccountId().toString()) ){
			sqlStr.append(" and accountId="+requestObj.getAccountId()+" ");
		} 
		if (requestObj !=null && StringUtils.isNotBlank(requestObj.getItemFlag()) ){
			sqlStr.append(" and itemFlag="+requestObj.getItemFlag()+" ");
		}
		if (requestObj !=null && StringUtils.isNotBlank(requestObj.getAccountItem()) ){
			sqlStr.append(" and accountItem="+requestObj.getAccountItem()+" ");
		}
		if (requestObj !=null && StringUtils.isNotBlank(requestObj.getYear()) && requestObj.getYear()!="0" ){
			sqlStr.append(" and year(accountDate)="+requestObj.getYear()+" ");
		}
		if (requestObj !=null && StringUtils.isNotBlank(requestObj.getYearMonth()) && requestObj.getYearMonth()!="0" ){
			sqlStr.append(" and year(accountDate)*100+month(accountDate)="+requestObj.getYearMonth()+" ");
		}
		sqlStr.append(" group by accountBook,accountId,itemFlag,accountItem,accountDate ");
		List<Object[]> objs = chgOverheadsRepository.listBySQL(sqlStr.toString());
		List<ChgOverheads> list = new ArrayList<ChgOverheads>();
		for (Object o[] : objs) {
			ChgOverheads chgOverHeads = new ChgOverheads();
			chgOverHeads.setMoney(new Double(String.valueOf(o[0])));
			chgOverHeads.setAccountBook(new Long(String.valueOf(o[1])));
			chgOverHeads.setAccountId(new Long(String.valueOf(o[2])));
			chgOverHeads.setItemFlag((String)o[3]);
			chgOverHeads.setAccountItem((String)o[4]);
			if (String.valueOf((o[5])).length() == 4){
				chgOverHeads.setYear(String.valueOf(o[5]));
			}else {
				chgOverHeads.setYearMonth(String.valueOf(o[5]));
			}
			list.add(chgOverHeads);
		}
		return new ResponseEntity<List<ChgOverheads>>(list, HttpStatus.OK);
	}
	
}
