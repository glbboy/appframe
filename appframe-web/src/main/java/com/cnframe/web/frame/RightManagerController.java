package com.cnframe.web.frame;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.cnframe.web.BaseController;

@Controller
@RequestMapping("/frame/rightmanager/")
public class RightManagerController  extends BaseController{
	@Autowired
    public RestTemplate restTemplate;
	
	@RequestMapping(value = "/role", method = RequestMethod.GET)
    public String role(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/frame/rightmanager/role";
	}
	@RequestMapping("/user")
    public String findSysUserByUserCode(Model model) {
		System.out.println("findSysUserByUserCode    begin");
		Object obj = restTemplate.getForObject("http://frameService/sys/sysuser/gelb", Object.class);
		model.addAttribute("sysUser", obj);
		System.out.println(obj.toString());
		System.out.println("findSysUserByUserCode    end");
        return "/user";
    }
}
