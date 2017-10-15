package com.cnframe.model.chg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cnframe.model.AbstractModel;

@Entity
@Table(name = "Chg_Account_Book")
public class ChgAccountBook extends AbstractModel{
	private static final long serialVersionUID = 1L;
	@Column(name = "ACCOUNTBOOK")
	private String accountBook;
	@Column(name = "ACCOUNTDESC")
	private String accountDesc;
	@Column(name = "ACCOUNTPIC")
	private String accountPic;
	@Column(name = "ACCOUNTID")
	private Long accountId;
	@Column(name = "SORT")
	private Integer sort;
	@Column(name = "INDICTCODE")
	private String inDictCode;
	@Column(name = "OUTDICTCODE")
	private String outDictCode;
	@Column(name = "TRANSDICTCODE")
	private String transDictCode;
	@Column(name = "LENDDICTCODE")
	private String lendDictCode;
	@Column(name = "IMGURL")
	private String imgUrl;
	@Column(name = "HOVERCLASS")
	private String hoverClass;
	@Column(name = "REMARK")
	private String remark;
	
	
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
	public String getAccountBook() {
		return accountBook;
	}
	public void setAccountBook(String accountBook) {
		this.accountBook = accountBook;
	}
	public String getAccountDesc() {
		return accountDesc;
	}
	public void setAccountDesc(String accountDesc) {
		this.accountDesc = accountDesc;
	}
	public String getAccountPic() {
		return accountPic;
	}
	public void setAccountPic(String accountPic) {
		this.accountPic = accountPic;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getInDictCode() {
		return inDictCode;
	}
	public void setInDictCode(String inDictCode) {
		this.inDictCode = inDictCode;
	}
	public String getOutDictCode() {
		return outDictCode;
	}
	public void setOutDictCode(String outDictCode) {
		this.outDictCode = outDictCode;
	}
	public String getTransDictCode() {
		return transDictCode;
	}
	public void setTransDictCode(String transDictCode) {
		this.transDictCode = transDictCode;
	}
	public String getLendDictCode() {
		return lendDictCode;
	}
	public void setLendDictCode(String lendDictCode) {
		this.lendDictCode = lendDictCode;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getHoverClass() {
		return hoverClass;
	}
	public void setHoverClass(String hoverClass) {
		this.hoverClass = hoverClass;
	}

}
