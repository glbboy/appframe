package com.cnframe.model.chg;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


import com.cnframe.model.AbstractModel;
import com.cnframe.modules.utils.DateJsonDeserializer;


@Entity
@Table(name = "Chg_Overheads")
public class ChgOverheads extends AbstractModel{
	private static final long serialVersionUID = 1L;
	@Column(name = "ACCOUNTBOOK")
	private Long accountBook;
	@Column(name = "ACCOUNTID")
	private Long accountId;
	@JsonDeserialize(using=DateJsonDeserializer.class)
	@Column(name = "ACCOUNTDATE")
	private Date accountDate;
	@Column(name = "ITEMFLAG")
	private String itemFlag;
	@Column(name = "ACCOUNTITEM")
	private String accountItem;
	@Column(name = "MONEY")
	private Double money;
	@Column(name = "USERCODE")
	private String userCode;
	@Column(name = "REMARK")
	private String remark;
	@Transient
	private String year = "0";
	@Transient
	private String yearMonth = "0";
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getAccountBook() {
		return accountBook;
	}
	public void setAccountBook(Long accountBook) {
		this.accountBook = accountBook;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Date getAccountDate() {
		return accountDate;
	}
	public void setAccountDate(Date accountDate) {
		this.accountDate = accountDate;
	}
	public String getItemFlag() {
		return itemFlag;
	}
	public void setItemFlag(String itemFlag) {
		this.itemFlag = itemFlag;
	}
	public String getAccountItem() {
		return accountItem;
	}
	public void setAccountItem(String accountItem) {
		this.accountItem = accountItem;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

}
