package com.cnframe.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class CommService {
	@Autowired
    public RestTemplate restTemplate;
	public String frameService(String name) {
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
        ResponseEntity<String> resp = restTemplate.exchange("http://SERVICE-HI/hi?name="+name, HttpMethod.GET, requestEntity, String.class);
        return resp.getBody();
    }
//	public String test(String url,Set requestMap){
//		RestTemplate rest = new RestTemplate();
//		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//		Set<Map.Entry<String, Object> > map  = requestMap.entrySet();
//		for (Entry<String, Object> entry : map) {
//			param.add(entry.getKey(), entry.getValue());
//		}
//		HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(
//				param, headers);
//		ResponseEntity<String> responseEntity;
//		try {
//			responseEntity = rest .exchange(url,
//					HttpMethod.POST, httpEntity, String.class);
//		} catch (RestClientException e) {
//			if(e instanceof HttpServerErrorException){
//				return ((HttpServerErrorException) e).getResponseBodyAsString();
//			}
//			e.printStackTrace();
//			return "{\"message\":\"远程服务调用!\",\"status\":500}";
//		}
//		//log.debug("code:"+responseEntity.getStatusCode());
//	}
}
