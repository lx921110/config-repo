package com.wetool.push.server.service;

import com.wetool.push.api.model.MsgType;
import com.wetool.push.api.model.client.CommodityReq;
import com.wetool.push.api.model.model.Commodity;
import com.wetool.push.api.model.server.CommodityResp;
import com.wetool.push.server.feign.CommodityFeignClient;
import com.wetool.push.server.model.CommodityReceive;
import com.wetool.push.server.model.Message;
import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

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
        ResponseEntity<Message<PagedResources<Resource<CommodityReceive>>>> resp =
                commodityFeignClient.list(commodityReq.getUpdateDate(), commodityReq.getMerchantId(), commodityReq.getSize(),commodityReq.getPage());

//        System.out.println(resp.getBody());
        Message<PagedResources<Resource<CommodityReceive>>> message = resp.getBody();
            /* 数据对象 */
        PagedResources<Resource<CommodityReceive>> pages = message.getData();
        Collection<Resource<CommodityReceive>> collComds = pages.getContent();
        ArrayList<Commodity> commoditys = new ArrayList<>();
        if (collComds != null) {
            collComds.forEach(co -> {
                        CommodityReceive cs = co.getContent();
                        Commodity commodity = cs.getCommodity();
                        commoditys.add(commodity);
                    }
            );
        }
        
		/* 判断是否获取全部查询信息 */
        if (pages.getMetadata() != null ){
            if (commodityReq.getPage() < pages.getMetadata().getTotalPages()) {
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