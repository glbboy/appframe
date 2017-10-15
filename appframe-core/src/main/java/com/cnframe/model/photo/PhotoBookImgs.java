package com.cnframe.model.photo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.cnframe.model.AbstractModel;
@Entity
@Table(name = "Photo_Book_Imgs")
public class PhotoBookImgs extends AbstractModel{
	private static final long serialVersionUID = 1L;
	@Column(name = "BOOKID")
	private Long bookId;
	@Column(name = "IMGID")
	private Long imgId;
	@Column(name = "CREATETIME")
	private Date createTime;
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public Long getImgId() {
		return imgId;
	}
	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@PrePersist
	protected void prePersist(){
		this.createTime = new Date();
	}
}
