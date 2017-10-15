package com.cnframe.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import java.util.Date;

import javax.persistence.Column;

import com.cnframe.modules.utils.DateUtil;
/*
 *  @PrePersist - before a new entity is persisted (added to the EntityManager).
	@PostPersist - after storing a new entity in the database (during commit or flush).
	@PostLoad - after an entity has been retrieved from the database.
	@PreUpdate - when an entity is identified as modified by the EntityManager.
	@PostUpdate - after updating an entity in the database (during commit or flush).
	@PreRemove - when an entity is marked for removal in the EntityManager.
	@PostRemove - after deleting an entity from the database (during commit or flush).
 */
@MappedSuperclass
public class CommModel extends AbstractModel{
	private static final long serialVersionUID = 1L;
	@Column(name = "CREATETIME")
	protected Date createTime;
	@Column(name = "ACTIVETIME")
	protected Date activeTime;
	@Column(name = "INACTIVETIME")
	protected Date inactiveTime;
	@Column(name = "LASTUPDATETIME")
	protected Date lastUpdateTime;
	@Column(name = "REMARK", length=4000)
	protected String remark;//备注默认长度4000
	
	public CommModel(){
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}

	public Date getInactiveTime() {
		return inactiveTime;
	}

	public void setInactiveTime(Date inactiveTime) {
		this.inactiveTime = inactiveTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@PrePersist
	protected void prePersist(){
		this.createTime = new Date();
		if (this.activeTime == null){
			this.activeTime = new Date();
		}
		if (this.inactiveTime == null){
			this.inactiveTime = DateUtil.stringtoDate("2099-12-31", DateUtil.LONG_DATE_FORMAT);
		}
		this.lastUpdateTime = new Date();
	}
	@PreUpdate
	protected void preUpdate(){
		this.lastUpdateTime = new Date();
	}
	
}
