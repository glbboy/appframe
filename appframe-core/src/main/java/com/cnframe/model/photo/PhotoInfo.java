package com.cnframe.model.photo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.cnframe.model.CommModel;
@Entity
@Table(name = "Photo_Info")
public class PhotoInfo extends CommModel{
	private static final long serialVersionUID = 1L;
	@Column(name = "IMGNAME")
	private String imgName;
	@Column(name = "IMGPATH")
	private String imgPath;
	@Column(name = "YEAR")
	private Integer year;
	@Column(name = "SHOTDATE")
	private Date shotDate;
	@Column(name = "REMARK")
	private String remark;
	@Formula("(select count(*) from Photo_Info t where t.year = year and sysdate()>activeTime and sysdate()<inactiveTime)")
	private Integer count;
	@Transient
	private Long bookId;
	@Transient
	private String bookName;
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Date getShotDate() {
		return shotDate;
	}
	public void setShotDate(Date shotDate) {
		this.shotDate = shotDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
