package com.cnframe.web.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.cnframe.msg.Body;
import com.cnframe.msg.Head;
import com.cnframe.web.BaseController;
import com.cnframe.web.Function;

@Controller
@RequestMapping("/frame/")
public class FrameMainController  extends BaseController{
	@Autowired
    public RestTemplate restTemplate;
	@RequestMapping("/exchange")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> exchange(@RequestBody Head head){
		Map<String, Object> requestPara =  new HashMap<String, Object>();
		System.out.println("1112323");
		System.out.println(head);
		System.out.println("2323");
		requestPara.put("head", head);
		//requestPara.put("body", body);
		HttpHeaders httpHeaders =  new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity<Map<String, Object>> requestEntity =  new RequestEntity<Map<String, Object>>(requestPara,httpHeaders,null,null);
		ResponseEntity<Map> responseEntity = restTemplate.exchange("http://frameService/exchange", HttpMethod.POST, requestEntity, Map.class);
		System.out.println(responseEntity.getBody());
		return new ResponseEntity<Map<String, Object>>(responseEntity.getBody(), HttpStatus.OK);
	}
	
	 public Map<String, Object> postForEntity(){  
         
         String url="http://localhost:8080/share-web/restpost";  
         //设置head  
         HttpHeaders requestHeaders = new HttpHeaders();   
         requestHeaders.set("head", "password");   
         requestHeaders.setContentType(MediaType.APPLICATION_JSON);  
         requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));  
         Map<String, Object> m=new HashMap<String, Object>();  
         m.put("param", "test");  
         m.put("red", 3);  
         //通过HttpEntity 设置参数 head  
         RequestEntity<?> request=new RequestEntity(m, requestHeaders,null,null);  
         //url地址 request请求参数 responsType 返回值类型 请求参数    
         ResponseEntity<Map> entitymap= restTemplate.postForEntity(url, request, Map.class);  
         Map<String, Object> map=entitymap.getBody();  
         return map;  
     }  
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("frame index");
        return "/frame/index";
    }
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("frame main");
		request.setAttribute("frame", "frame");
        return "/frame/main";
    }
	@RequestMapping(value = "/getMenuList", method = RequestMethod.POST)
    @ResponseBody
    public List<Function> getMenuList() {
    	ArrayList<Function> funList = new ArrayList<Function>();
    	Function fun = new Function();
    	fun.setIcon("fa fa-home");
    	fun.setName("首页");
    	fun.setLevelCode("000001");
    	fun.setUrl("/index");
    	funList.add(fun);
    	fun = new Function();
    	fun.setIcon("fa fa-money");
    	fun.setName("投资系统");
    	fun.setLevelCode("000002");
    	fun.setUrl("/invest/index");
    	funList.add(fun);
    	fun = new Function();
    	fun.setIcon("fa fa-reorder");
    	fun.setName("记账系统");
    	fun.setLevelCode("000003");
    	fun.setUrl("/charge/index");
    	funList.add(fun);
    	fun = new Function();
    	fun.setIcon("fa fa-photo");
    	fun.setName("相册管理");
    	fun.setLevelCode("000004");
    	fun.setUrl("/photo/index");
    	funList.add(fun);
    	
    	fun = new Function();
    	fun.setIcon("fa fa-key");
    	fun.setName("系统管理");
    	fun.setLevelCode("000009");
    	fun.setUrl("/sys/index");
    	funList.add(fun);
    	
    	fun = new Function();
    	fun.setIcon("fa fa-book");
    	fun.setName("文章");
    	fun.setLevelCode("000010");
    	funList.add(fun);
    	fun = new Function();
    	fun.setIcon("fa fa-user");
    	fun.setName("用户管理");
    	fun.setLevelCode("000003000001");
    	fun.setUrl("/homePage");
    	funList.add(fun);
    	fun = new Function();
    	fun.setIcon("fa fa-users");
    	fun.setName("角色管理");
    	fun.setLevelCode("000003000001");
    	fun.setUrl("/frame/user/list");
    	funList.add(fun);
    	return funList;
    }
}
