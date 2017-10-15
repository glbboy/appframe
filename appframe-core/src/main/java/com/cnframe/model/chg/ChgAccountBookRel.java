package com.cnframe.model.chg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cnframe.model.AbstractModel;

@Entity
@Table(name = "Chg_Account_Book_Rel")
public class ChgAccountBookRel extends AbstractModel{
	private static final long serialVersionUID = 1L;
	@Column(name = "ACCOUNTBOOK")
	private Long accountBook;
	@Column(name = "ACCOUNTID")
	private Long accountId;
	@Column(name = "SORT")
	private Integer sort;
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
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
	
}
