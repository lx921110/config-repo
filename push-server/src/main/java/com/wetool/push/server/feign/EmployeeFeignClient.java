package com.wetool.push.server.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import com.wetool.push.server.model.Message;
import feign.RequestLine;

@FeignClient("shop-server")
public interface EmployeeFeignClient {
	
	@RequestLine("GET " + "/employee/list ")
	ResponseEntity<Message<?>> listEmployee();
}