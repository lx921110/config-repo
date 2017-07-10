package com.wetool.push.server.feign;

import com.wetool.push.api.model.model.CategoryTree;
import com.wetool.push.server.model.CommodityReceive;
import com.wetool.push.server.model.Message;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

@FeignClient("commodity-server")
public interface CommodityFeignClient {

    /**
     * 根据修改时间和请求记录数查询
     *
     * @param updateTime 修改时间
     * @param merchantId 商家ID
     * @param size       请求查询条数
     * @return
     */
    @RequestLine("GET " + "/commodity/list?updateTime={updateTime}&merchantId={merchantId}&page={page}&size={size}&sort=id,asc")
    ResponseEntity<Message<PagedResources<Resource<CommodityReceive>>>> list(@Param("updateTime") String updateTime, @Param("merchantId") Long merchantId, @Param("size") Integer size,@Param("page") Long page);

    @RequestLine("GET " + "/category/tree?merchantId={merchantId}")
    ResponseEntity<Message<ArrayList<CategoryTree>>> categorylist(@Param("merchantId") Long merchantId);
}
