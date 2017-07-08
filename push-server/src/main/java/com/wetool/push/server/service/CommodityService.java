package com.wetool.push.server.service;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.wetool.push.api.model.MsgType;
import com.wetool.push.api.model.client.CommodityReq;
import com.wetool.push.api.model.model.Commodity;
import com.wetool.push.api.model.server.CommodityResp;
import com.wetool.push.server.feign.CommodityFeignClient;
import com.wetool.push.server.model.Message;
import feign.Feign;

@Component
public class CommodityService {

    @Autowired
    Feign.Builder builder;

    @Value("${url.commodity-server}")
    private String url;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public CommodityResp commSync(CommodityReq commodityReq) throws Exception {
    	url = "http://192.168.1.91:16010";
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
        if (collComds != null) {
        	Commodity commodity = null;
            for (Resource<Commodity> co : collComds) {
            	Commodity cs = co.getContent();
            	commodity = new Commodity();
            	BeanUtils.copyProperties(cs, commodity);
            	if (cs.getResources() != null) {//图片路径处理
            		String rul = cs.getResources().getResUrl();
            		commodity.setPicPath(rul);
            		commodity.setResources(null);
            	}
            	commoditys.add(commodity);
    		}
        }
        
		/* 判断是否获取全部查询信息 */
        if ( pages.getMetadata() != null && 
        		commodityReq.getSize() < pages.getMetadata().getTotalElements()) {
            flag = false;
        }
        CommodityResp commodityResp = new CommodityResp(MsgType.COMMODITY_RESP);
		commodityResp.setCommoditys(commoditys);
        commodityResp.setFlag(flag);
        return commodityResp;
    }
}