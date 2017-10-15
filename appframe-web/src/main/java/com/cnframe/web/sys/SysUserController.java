package com.cnframe.web.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cnframe.model.sys.SysUser;

@Controller
@RequestMapping("/sys/")
public class SysUserController {
	private final static String FRAME_SERVICE_URL="http://frameService/sys/";
	@Autowired
    public RestTemplate restTemplate;
	@RequestMapping("/add")
	@ResponseBody
	public ResponseEntity<SysUser> add(@RequestBody SysUser sysUser){		
		HttpHeaders httpHeaders =  new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity<SysUser> requestEntity =  new RequestEntity<SysUser>(sysUser,httpHeaders,null,null);
		ResponseEntity<SysUser> responseEntity = restTemplate.exchange(FRAME_SERVICE_URL+"add", HttpMethod.POST, requestEntity, SysUser.class);
		System.out.println(responseEntity.getBody());
		return new ResponseEntity<SysUser>(responseEntity.getBody(), HttpStatus.OK);
	}
	@RequestMapping("/update")
	@ResponseBody
	public ResponseEntity<SysUser> update(@RequestBody SysUser sysUser){		
		HttpHeaders httpHeaders =  new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity<SysUser> requestEntity =  new RequestEntity<SysUser>(sysUser,httpHeaders,null,null);
		ResponseEntity<SysUser> responseEntity = restTemplate.exchange(FRAME_SERVICE_URL+"update", HttpMethod.POST, requestEntity, SysUser.class);
		System.out.println(responseEntity.getBody());
		return new ResponseEntity<SysUser>(responseEntity.getBody(), HttpStatus.OK);
	}
	@RequestMapping("/findall")
	@ResponseBody
	public ResponseEntity<List<SysUser>>  findall(){		
		HttpHeaders httpHeaders =  new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity<SysUser> requestEntity =  new RequestEntity<SysUser>(null,httpHeaders,null,null);
		ParameterizedTypeReference<List<SysUser>> responseType = new ParameterizedTypeReference<List<SysUser>>(){};
		ResponseEntity<List<SysUser>> responseEntity = restTemplate.exchange(FRAME_SERVICE_URL+"findall", HttpMethod.POST, requestEntity, responseType);
		System.out.println(responseEntity.getBody());
		return new ResponseEntity<List<SysUser>>(responseEntity.getBody(), HttpStatus.OK);
	}
	@RequestMapping("/delete")
	@ResponseBody
	public ResponseEntity<SysUser> delete(@RequestBody SysUser sysUser){		
		HttpHeaders httpHeaders =  new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity<SysUser> requestEntity =  new RequestEntity<SysUser>(sysUser,httpHeaders,null,null);
		ResponseEntity<SysUser> responseEntity = restTemplate.exchange(FRAME_SERVICE_URL+"delete", HttpMethod.DELETE, requestEntity, SysUser.class);
		System.out.println(responseEntity.getBody());
		return new ResponseEntity<SysUser>(responseEntity.getBody(), HttpStatus.OK);
	}
	
	
}
