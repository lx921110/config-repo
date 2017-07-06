package com.wetool.push.server.feign;

import java.util.Map;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import feign.Param;
import feign.RequestLine;

@FeignClient("auth-server")
public interface LoginFeignClient {
	
	@RequestLine("GET " + "/oauth/check_token?token={token}")
	ResponseEntity<Map<String, Object>> checkToken(@Param("token") String token);
}