package com.cnframe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cnframe.model.sys.SysUser;
import com.cnframe.msg.Fault;
import com.cnframe.msg.Head;
import com.cnframe.msg.TransMsg;
import com.cnframe.repository.SysUserRepository;
@SuppressWarnings({"unchecked","rawtypes"})
@RestController
public class CommController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SysUserRepository sysUserRepository;
	
	@SuppressWarnings("unused")
	public void test(){
		Sort sort = new Sort(Direction.DESC, "id");
	    Pageable pageable = new PageRequest(1, 10, sort);
	    //baseRepository.findAll(pageable);
	}
	@RequestMapping("/loadData")
    @ResponseBody
    public Map<String, Object> loadData(String reqObj) throws Exception {
		//用于接收返回数据(配置、分页、数据)
        Map<String, Object> map = new HashMap<>();
        return map;
    }
	@RequestMapping(value = "/frame", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> findAll() {
		//List<Object> objs = (List<Object>) baseRepository.findAll();
		List<Object> objs = null;
//		if(objs.isEmpty()){
//			return new ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
//		}
		return new ResponseEntity<List<Object>>(objs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/frame/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteUser(@PathVariable("id") long id) {
//		Object obj = baseRepository.findOne(id);
//		if (obj == null) {
//			System.out.println("Unable to delete. User with id " + id + " not found");
//			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
//		}
//
//		baseRepository.delete(null);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/request", method = RequestMethod.POST)
    public <T> ResponseEntity request(RequestEntity<T> requestEntity) {
        ResponseEntity<T> responseEntity = ResponseEntity.ok(requestEntity.getBody());
        return responseEntity;

    }
	
	@RequestMapping(value = "/frame/user1", method = RequestMethod.GET)
	public ResponseEntity<List<SysUser>> listAllUsers() {
		List<SysUser> sysUsers = (List<SysUser>) sysUserRepository.findAll();
		if(sysUsers.isEmpty()){
			return new ResponseEntity<List<SysUser>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<SysUser>>(sysUsers, HttpStatus.OK);
	}
	@RequestMapping(value = "/frame/user2", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listAllUsers2() {
		List<SysUser> sysUsers = (List<SysUser>) sysUserRepository.findAll();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		responseHeaders.set("userId", "userId");
		System.out.println("testsfd");
		Map<String, Object> map = new HashMap<>();
		map.put("sys_user", sysUsers);
		map.put("sys_user1", sysUsers);
		if(sysUsers.isEmpty()){
			return new ResponseEntity<Map<String, Object>>(responseHeaders,HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<Map<String, Object>>(map,responseHeaders, HttpStatus.OK);
	}
	@RequestMapping("/exchange")
	public ResponseEntity<Map<String, Object>> exchange(RequestEntity <Map<String, Object>> requestEntity){
		Map<String, Object> body = requestEntity.getBody();
		System.out.println(body.get("userid"));
		System.out.println(body.get("head"));
		
		//JSONObject jsonObject = JSON.parseObject(body.get("head").toString());
		System.out.println(body);
		JSONObject json = new JSONObject(body);
		
		System.out.println("json:"+json);
		Head head = new Head();
		head.setFunctionId("testfundid");
		head = JSONObject.toJavaObject(json.getJSONObject("head"), Head.class);
		System.out.println("head.getFunctionId():"+head.getFunctionId());
		Map<String, Object> responseEntity = new HashMap<>();
		responseEntity.put("head", head);
		Fault fault = new Fault();
		fault.setFaultCode("faultCode");
		responseEntity.put("fault", fault);
		List<SysUser> sysUsers = (List<SysUser>) sysUserRepository.findAll();
		
		Map<String,Object> users = new HashMap();
		users.put("status", "statues");
		users.put("sysUsers", sysUsers);
		users.put("sysRoles", sysUsers);
		responseEntity.put("body", users);
		return new ResponseEntity<Map<String, Object>>(responseEntity, HttpStatus.OK);
	}
	@RequestMapping(value="/restpost",method=RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> restPost(@RequestBody Map<String,Object> map){  
          
        System.out.println(map);  
        map.put("aaa", "ffffff");  
        return map;  
    }  
	@RequestMapping(value = "/frame/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SysUser> getUser(@PathVariable("id") Long id) {
		System.out.println("Fetching User with id " + id);
		SysUser sysUser = sysUserRepository.findById(id);
		if (sysUser == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<SysUser>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<SysUser>(sysUser, HttpStatus.OK);
	}
	@RequestMapping(value = "/frame/doExe",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody TransMsg doExe(@RequestBody TransMsg transMsg){
		System.out.println("111111111113");
		TransMsg returnMsg = new TransMsg();
		List<SysUser> sysUsers = (List<SysUser>) sysUserRepository.findAll();
		System.out.println("111111111114");
		returnMsg.getBody().getOutPara().put("sysUser", sysUsers);
		System.out.println("111111111115");
		return returnMsg;
	}
//	 ResponseEntity<String> results = restTemplate.exchange(url,HttpMethod.GET, null, String.class, params);
//	    // 借助com.fasterxml.jackson.databind.ObjectMapper 对象来解析嵌套的json字符串    
//	    ObjectMapper mapper = new ObjectMapper(); mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//	    PageInfo<Product> page = mapper.readValue(results.getBody(), new TypeReference<PageInfo<Product>>() { });
}
