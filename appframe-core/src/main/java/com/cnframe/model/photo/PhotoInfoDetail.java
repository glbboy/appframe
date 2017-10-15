package com.cnframe.model.photo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cnframe.model.AbstractModel;
@Entity
@Table(name = "Photo_Info_Detail")
public class PhotoInfoDetail extends AbstractModel{
	private static final long serialVersionUID = 1L;
	@Column(name = "IMGID")
	private Long imgId;
	@Column(name = "TAGMAIN")
	private String tagMain;
	@Column(name = "TAGCODE")
	private String tagCode;
	@Column(name = "TAGVALUE")
	private String tagValue;
	public Long getImgId() {
		return imgId;
	}
	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}
	public String getTagMain() {
		return tagMain;
	}
	public void setTagMain(String tagMain) {
		this.tagMain = tagMain;
	}
	public String getTagCode() {
		return tagCode;
	}
	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
	}
	public String getTagValue() {
		return tagValue;
	}
	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}
}
