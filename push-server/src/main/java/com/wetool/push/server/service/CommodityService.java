package com.wetool.push.server.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.wetool.push.api.model.MsgType;
import com.wetool.push.api.model.Result;
import com.wetool.push.api.model.S2ClientResp;
import com.wetool.push.api.model.client.CommodityReq;
import com.wetool.push.api.model.model.Commodity;
import com.wetool.push.api.model.model.Commodity2;
import com.wetool.push.api.model.server.CommodityResp;
import com.wetool.push.server.feign.CommodityFeignClient;
import com.wetool.push.server.model.Message;

import feign.Feign;

@Component
public class CommodityService {

    @Autowired
    Feign.Builder builder;

    //@Value("${url.commodity-server}")
    private String url;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public CommodityResp commSync(CommodityReq commodityReq) throws Exception {
//        try {
        url = "http://127.0.0.1:16010";
        Boolean flag = true;
            /* 商品获取接口 */
        CommodityFeignClient commodityFeignClient = builder.target(CommodityFeignClient.class, url);
            /* 获取接口返回数据 */
        ResponseEntity<Message<PagedResources<Resource<Commodity>> >> resp =
        		commodityFeignClient.list(commodityReq.getUpdateDate(), commodityReq.getMerchantId(),commodityReq.getSize());
        System.out.println(resp.getBody());
        Message message = resp.getBody();
			/* 数据对象 */
        PagedResources<Resource<Commodity>> pages =  (PagedResources<Resource<Commodity>>) message.getData();
        Collection<Resource<Commodity>> collComds = pages.getContent();
        ArrayList<Commodity> commoditys = new ArrayList<>();
        ArrayList<Commodity2> commodity2s = new ArrayList<>();
        if (collComds != null) {
        /* 商品信息数据集合 */
        commoditys = collComds.stream()
        		.filter(item -> item != null)
        		.collect(
        				() -> new ArrayList<Commodity>(),
        				(list, item) -> list.add(item.getContent()),
        				(list1, list2) -> list1.addAll(list2)
        				);
        }
        Commodity2 commodity2 = null;
        for (Commodity commodity : commoditys) {
        	commodity2 = new Commodity2();
        	BeanUtils.copyProperties(commodity, commodity2);
        	commodity2s.add(commodity2);
		}
		/* 判断是否获取全部查询信息 */
        if ( pages.getMetadata() != null && 
        		commodityReq.getSize() < pages.getMetadata().getTotalElements()) {
            flag = false;
        }
        CommodityResp commodityResp = new CommodityResp(MsgType.COMMODITY_RESP);
		commodityResp.setCommoditys(commodity2s);
        commodityResp.setFlag(flag);
        return commodityResp;
    }
}