package com.cnframe.model.chg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cnframe.model.AbstractModel;

@Entity
@Table(name = "Chg_Account")
public class ChgAccount extends AbstractModel{
	private static final long serialVersionUID = 1L;
	@Column(name = "ACCOUNTNAME")
	private String accountName;
	@Column(name = "ACCOUNTMONEY")
	private Double accountMoney;
	@Column(name = "SORT")
	private Integer sort;
	@Column(name = "REMARK")
	private String remark;
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Double getAccountMoney() {
		return accountMoney;
	}
	public void setAccountMoney(Double accountMoney) {
		this.accountMoney = accountMoney;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
