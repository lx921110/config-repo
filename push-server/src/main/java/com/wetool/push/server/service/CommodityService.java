package com.wetool.push.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.wetool.push.api.model.MsgType;
import com.wetool.push.api.model.client.CommodityReq;
import com.wetool.push.api.model.model.Commodity;
import com.wetool.push.api.model.server.CommodityResp;
import com.wetool.push.server.feign.CommodityFeignClient;
import com.wetool.push.server.model.CommodityReceive;
import com.wetool.push.server.model.Message;

import feign.Feign;

@Component
public class CommodityService {

    @Autowired
    Feign.Builder builder;

    @Value("${url.commodity-server}")
    private String url;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public CommodityResp commSync(CommodityReq commodityReq) throws Exception {
        url = "http://192.168.1.91:16010";
        Boolean flag = true;
            /* 商品获取接口 */
        CommodityFeignClient commodityFeignClient = builder.target(CommodityFeignClient.class, url);
            /* 获取接口返回数据 */
        if ("".equals(commodityReq.getUpdateDate()) || commodityReq.getUpdateDate() == null) {
        	commodityReq.setUpdateDate(null);
        }
        ResponseEntity<Message<List<CommodityReceive>>> resp =
                commodityFeignClient.findByUpTime(commodityReq.getUpdateDate(), commodityReq.getMerchantId(), commodityReq.getSize(),commodityReq.getPage());

        System.out.println(resp.getBody());
        Message<List<CommodityReceive>> message = resp.getBody();
        
            /* 数据对象 */
        ArrayList<Commodity> commoditys = new ArrayList<>();
        if (message.getData() != null) {
        	message.getData().forEach(co -> {
                        Commodity commodity = co.getCommodity();
                        commoditys.add(commodity);
                    }
            );
        }
        
		/* 判断是否获取全部查询信息 */
        if (commoditys != null || commoditys.size() > 0){
            if (commodityReq.getPage() + 1 < message.getData().get(0).getTotalPage()) {
            	 flag = false;
            }
        }
        CommodityResp commodityResp = new CommodityResp(MsgType.COMMODITY_RESP);
        commodityResp.setCommoditys(commoditys);
        commodityResp.setPage(commodityReq.getPage());
        commodityResp.setFlag(flag);
        return commodityResp;
    }
}