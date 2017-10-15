package com.cnframe.service;

import java.util.List;

import com.cnframe.model.sys.SysApp;
import com.cnframe.model.sys.SysDict;
import com.cnframe.model.sys.SysDictDetail;
import com.cnframe.model.sys.SysMenu;

public interface IndexService {
	public String sayHello(String name);
	public List<SysApp> getSysAppList();
	public List<SysMenu> getSysMenuList(String appCode,String userCode);
	public List<SysDict> getSysDictList();
	public List<SysDictDetail> getSysDictDetailList(String dictCode);
}
