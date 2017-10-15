package com.cnframe.web.frame;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.cnframe.msg.TransMsg;

@Controller
@RequestMapping("/frame/user")
public class UserController {
	@Autowired
    public RestTemplate restTemplate;
	
	@RequestMapping(value="list", method=RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
		HttpHeaders headers = new HttpHeaders();  
        /*headers.add(HttpHeadersImpl.ACCEPT, "application/json"); 
        headers.add(HttpHeadersImpl.ACCEPT_ENCODING, "gzip"); 
        headers.add(HttpHeadersImpl.CONTENT_ENCODING, "UTF-8"); 
        headers.add(HttpHeadersImpl.CONTENT_TYPE, 
                "application/json; charset=UTF-8"); 
        headers.add(HttpHeadersImpl.COOKIE, token); 
        headers.add("Token", token);*/  
        headers.add("Accept", "application/json");  
        headers.add("Accpet-Encoding", "gzip");  
        headers.add("Content-Encoding", "UTF-8");  
        headers.add("Content-Type", "application/json; charset=UTF-8");  
        headers.add("Token", "token");  
      //设置参数
  		Map<String, String> hashMap = new LinkedHashMap<String, String>();
  		hashMap.put("random", "1234556");
  		hashMap.put("orderNo", "Z20170327110912921426");
  		hashMap.put("requestSource","");
        //HttpEntity<String> formEntity = new HttpEntity<String>(data, headers); 
        RequestEntity<Map<String, String>> requestEntity = new RequestEntity<Map<String, String>>(hashMap,headers,null,null);
		ResponseEntity<String> resp = restTemplate.exchange("http://frameService/frame/user2", HttpMethod.GET, requestEntity, String.class);
		System.out.println(resp.getHeaders());
        System.out.println(resp.getBody());
		//Page<User> datas = userService.findAll(PageableUtil.basicPage(page));
        //model.addAttribute("datas", datas);
        return "frame/user/list";
    }
	
	@RequestMapping(value="user", method=RequestMethod.GET)
    public String user(Model model, HttpServletRequest request) {
		System.out.println("111111111110");
		TransMsg transMsg = new TransMsg();
		System.out.println("11111111111");
		TransMsg resp = restTemplate.postForObject("http://frameService/frame/doExe", transMsg, TransMsg.class);
		System.out.println("111111111112");
        System.out.println(resp);
		//Page<User> datas = userService.findAll(PageableUtil.basicPage(page));
        //model.addAttribute("datas", datas);
        return "frame/user/user";
    }
	
//	 @RequestMapping("/query/{page}/{pageSize}")  
//	    public PageInfo query(@PathVariable Integer page, @PathVariable Integer pageSize) {  
//	        if(page!= null && pageSize!= null){  
//	            PageHelper.startPage(page, pageSize);  
//	        }  
//	        List<User> users = userMapper.list();  
//	        return new PageInfo(users);  
//	    }  
	//@RequestMapping(method = RequestMethod.POST,value = "/add")
    //public String create( UserCommond user){
        //userService.create(UserAssembler.commondToDomain(user));
      //  return "redirect:/user";
    //}
//	@RequestMapping(value = "/{id}/modify", method = RequestMethod.POST)
//    public String modify(@PathVariable("id") String id,  UserCommond user) {
//        userService.modify(UserAssembler.commondToDomain(id, user));
//        return "redirect:/user";
//    }
//
//    @RequestMapping(value = "/{id}/status",method = RequestMethod.PUT)
//    @ResponseBody
//    public void switchStatus(@PathVariable("id") String id,@RequestParam("disable") boolean disable){
//        userService.switchStatus(id,disable);
//    }
//    @RequestMapping(value = "/{id}/delete",method = RequestMethod.DELETE)
//    @ResponseBody
//    public void delete(@PathVariable("id")String id){
//         userService.delete(id);
//    }
//
//    @RequestMapping(value = "/form",method = RequestMethod.GET)
//    public String form(@RequestParam(value = "id",required = false)String id, Model model){
//        String api="/user/add";
//        if(StringUtils.isNotBlank(id)){
//            model.addAttribute("acount",UserAssembler.domainToDto(userService.get(id)));
//            api="/user/"+id+"/modify";
//        }
//        model.addAttribute("api",api);
//        return  "user/form";
//    }
//
//    @RequestMapping(method = RequestMethod.GET)
//    public String list(Model model){
//        model.addAttribute("list",UserAssembler.domainToDto(userService.list()));
//        return "user/list";
//    }
//
//    @RequestMapping(value = "/{id}/grant-role",method = RequestMethod.POST)
//    public String grantRole(@PathVariable("id") String id,  String[] rid) {
//        if(rid==null){
//            rid=new String[0];
//        }
//        userService.grantRole(id, Lists.newArrayList(rid));
//        return "redirect:/user";
//    }
//
//
//    @RequestMapping(value = "/{id}/select-role", method = RequestMethod.GET)
//    public String selectRole(@PathVariable("id") String id,Model model) {
//        model.addAttribute("list",userService.selectRoles(id));
//        model.addAttribute("api","/user/"+id+"/grant-role");
//        return "user/grant-role";
//    }
//
//    @RequestMapping(value = "/profile", method = RequestMethod.GET)
//    public String myinfo() {
//        return "user/profile";
//    }
//
//    @RequestMapping(value = "/modify-profile", method = RequestMethod.POST)
//    public String modifyProfile( ProfileCommand myInfo) {
//        this.userService.modify(UserAssembler.profileToDomain(SecurityUtil.getUid(), myInfo));
//        SecurityUtil.getUser().setEmail(myInfo.getEmail());
//        return "user/profile";
//    }
}
