package com.wetool.push.server.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import com.wetool.push.server.model.MacVersion;
import com.wetool.push.server.model.Message;
import feign.RequestLine;

@FeignClient("mac-server")
public interface MacFeignClient {
	
	@RequestLine("POST " + "/version/save")
	ResponseEntity<Message<?>> save(MacVersion macVersion);
}