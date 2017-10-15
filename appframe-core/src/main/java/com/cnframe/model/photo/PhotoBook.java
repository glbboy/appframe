package com.cnframe.model.photo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.cnframe.model.CommModel;
@Entity
@Table(name = "Photo_Book")
public class PhotoBook extends CommModel{
	private static final long serialVersionUID = 1L;
	@Column(name = "BOOKNAME")
	private String bookName;
	@Column(name = "BOOKDESC")
	private String bookDesc;
	@Column(name = "SORT")
	private Integer sort;
	@Column(name = "REMARK")
	private String remark;
	@Formula("(select count(*) from Photo_Book_Imgs t where t.bookId = id)")
	private Integer count;
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookDesc() {
		return bookDesc;
	}
	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
}
