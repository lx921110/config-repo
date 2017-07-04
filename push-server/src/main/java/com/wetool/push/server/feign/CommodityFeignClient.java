package com.wetool.push.server.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;

import com.wetool.push.server.model.Message;

import feign.Param;
import feign.RequestLine;

@FeignClient("commodity-server")
public interface CommodityFeignClient {

	/**
	 * 根据修改时间和请求记录数查询
	 * @param updateTime 修改时间
	 * @param merchantId 商家ID
	 * @param size 请求查询条数
	 * @return
	 */
	@RequestLine("GET " + "/commodity/list?updateTime={updateTime}&merchantId={merchantId}&page=0&size={size}&sort=id,asc")
	ResponseEntity<Message<?>> list(@Param("updateTime") String updateTime,@Param("merchantId") Long merchantId, @Param("size") Integer size);
	
}
