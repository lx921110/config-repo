package com.wetool.push.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.wetool.push.server.feign.EmployeeFeignClient;
import com.wetool.push.server.model.Message;
import feign.Feign;

@Component
public class CommodityService {
	
	@Autowired
	Feign.Builder builder;
	
	public void commSync(String token) {
		String url = "http://192.168.1.110:16020/";
		EmployeeFeignClient employeeFeignClient = builder.target(EmployeeFeignClient.class, url);
		ResponseEntity<Message<?>> resp = employeeFeignClient.listEmployee();
		System.out.println(resp.getBody());
	}
}