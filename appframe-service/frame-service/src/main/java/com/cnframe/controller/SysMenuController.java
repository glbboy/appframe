package com.cnframe.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnframe.model.sys.SysMenu;
import com.cnframe.repository.SysMenuRepository;
import com.cnframe.repository.SysMenuSpec;

@CrossOrigin
@RestController
public class SysMenuController extends BaseController{
	@Autowired
	private SysMenuRepository sysMenuRepository;
	@RequestMapping("/sysmenu/findall")
	public ResponseEntity<List<SysMenu>> findAll(RequestEntity <SysMenu> requestEntity){
		SysMenu sysMenu;
		if (requestEntity==null || requestEntity.getBody() == null){
			sysMenu = new SysMenu();
		}else {
			sysMenu = requestEntity.getBody();
		}
		SysMenuSpec spec = new SysMenuSpec(sysMenu);
		List<SysMenu> sysMenuList = sysMenuRepository.findAll(spec);
		List<SysMenu> returnMenuList =  new ArrayList<SysMenu>();
		for (int i=0;i<sysMenuList.size();i++){
			SysMenu menu = sysMenuList.get(i);
			if (StringUtils.isBlank(menu.getPmenuCode())){
				List<SysMenu> childMenuList = new ArrayList<SysMenu>();
				menu.setChildMenuList(childMenuList);
				returnMenuList.add(menu);
			}else {
				for(int j=0;j<returnMenuList.size();j++){
					SysMenu fmenu = returnMenuList.get(j);
					if (fmenu.getMenuCode().equals(menu.getPmenuCode())){
						if (StringUtils.isBlank(menu.getMenuNavigateUrl())){
							menu.setMenuNavigateUrl(menu.getMenuUrl());
						}
						fmenu.getChildMenuList().add(menu);
						continue;
					}
				}
			}
		}
		//System.out.println(new ResponseEntity<List<SysMenu>>(sysMenuList, HttpStatus.OK));
		return new ResponseEntity<List<SysMenu>>(returnMenuList, HttpStatus.OK);
	}
}
