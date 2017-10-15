package com.cnframe.model.sys;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cnframe.model.AbstractModel;

@Entity
@Table(name = "SYS_MENU")
public class SysMenu extends AbstractModel{
	private static final long serialVersionUID = 1L;
	@Column(name = "MENUCODE", nullable = false)
	private String menuCode;
	@Column(name = "MENUNAME", nullable = false)
	private String menuName;
	@Column(name = "MENUDESC")
	private String menuDesc;
	@Column(name = "PMENUCODE")
	private String pmenuCode;
	@Column(name = "SORT")
	private Integer sort;
	@Column(name = "MENUURL")
	private String menuUrl;
	@Column(name = "MENUPIC")
	private String menuPic;
	@Column(name = "APPCODE" , nullable = false)
	private String appCode;
	@Column(name = "MENUTYPE")
	private String menuType;//01父菜单、02菜单、03功能点
	@Column(name = "REMARK", length=4000)
	private String remark;
	@Column(name = "LEVELNUM")
	private long levelNum;
	@Column(name = "MENUCLASS")
	private String menuClass;
	@Column(name = "MENUNAVIGATEURL")
	private String menuNavigateUrl;
	@Transient
	private List<SysMenu> childMenuList;
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuDesc() {
		return menuDesc;
	}
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}
	public String getPmenuCode() {
		return pmenuCode;
	}
	public void setPmenuCode(String pmenuCode) {
		this.pmenuCode = pmenuCode;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getMenuPic() {
		return menuPic;
	}
	public void setMenuPic(String menuPic) {
		this.menuPic = menuPic;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getLevelNum() {
		return levelNum;
	}
	public void setLevelNum(long levelNum) {
		this.levelNum = levelNum;
	}
	public String getMenuClass() {
		return menuClass;
	}
	public void setMenuClass(String menuClass) {
		this.menuClass = menuClass;
	}
	public String getMenuNavigateUrl() {
		return menuNavigateUrl;
	}
	public void setMenuNavigateUrl(String menuNavigateUrl) {
		this.menuNavigateUrl = menuNavigateUrl;
	}
	public List<SysMenu> getChildMenuList() {
		return childMenuList;
	}
	public void setChildMenuList(List<SysMenu> childMenuList) {
		this.childMenuList = childMenuList;
	}
	
}
