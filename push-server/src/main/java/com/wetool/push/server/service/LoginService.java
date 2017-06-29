package com.wetool.push.server.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.wetool.push.server.ContextHolder;
import com.wetool.push.server.feign.AuthFeignClient;
import feign.Feign;

@Component
public class LoginService {
	
	@Autowired
	Feign.Builder builder;
	
	@Value("${url.auth-server}")
	private String url;
	
	public void login() {
		AuthFeignClient authFeignClient = builder.target(AuthFeignClient.class, url);
		ResponseEntity<Map> resp = authFeignClient.checkToken(ContextHolder.get());
//		user_name
		System.out.println("login !!! " + resp.getBody());
	}
}