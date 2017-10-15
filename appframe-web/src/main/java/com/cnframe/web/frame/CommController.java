package com.cnframe.web.frame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/frame/")
public class CommController {
	private final static String FRAME_SERVICE_URL="http://frameService/frame/";
	@Autowired
    public RestTemplate restTemplate;
	@RequestMapping("/save")
	@ResponseBody
	public ResponseEntity<Object> save(@RequestBody Object obj){	
		//@RequestParam("sign") String sign,
		System.out.println("111111111111111");//Token令牌 token    时间戳timestamp   nonceStr: '', // 必填，生成签名的随机串 sign
		HttpHeaders httpHeaders =  new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		System.out.println(obj);
		RequestEntity<Object> requestEntity =  new RequestEntity<Object>(obj,httpHeaders,null,null);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(FRAME_SERVICE_URL+"save", HttpMethod.POST, requestEntity, Object.class);
		System.out.println(responseEntity.getBody());
		return new ResponseEntity<Object>(responseEntity.getBody(), HttpStatus.OK);
	}
}
