package com.wetool.push.server.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;

import com.wetool.push.server.model.MacVersion;
import com.wetool.push.server.model.Message;
import feign.Param;
import feign.RequestLine;

@FeignClient("mac-server")
public interface MacFeignClient {
	
	@RequestLine("PATCH " + "/version/save/{macSn}")
	ResponseEntity<Message<?>> save(@Param("macSn") String macSn, MacVersion macVersion);
}