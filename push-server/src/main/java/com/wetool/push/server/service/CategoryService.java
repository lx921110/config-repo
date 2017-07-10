package com.wetool.push.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.wetool.push.api.model.client.CategoryReq;
import com.wetool.push.api.model.model.CategoryTree;
import com.wetool.push.api.model.server.CategoryResp;
import com.wetool.push.server.feign.CommodityFeignClient;
import com.wetool.push.server.model.Message;

import feign.Feign;

@Component
public class CategoryService {

	@Autowired
	Feign.Builder builder;

	// @Value("${url.commodity-server}")
	private String url;
	
	public CategoryResp categorySync(CategoryReq categoryReq){
		url = "http://192.168.1.91:16010";
		/* 商品获取接口 */
		CommodityFeignClient commodityFeignClient = builder.target(CommodityFeignClient.class, url);
		ResponseEntity<Message<ArrayList<CategoryTree>>> res = commodityFeignClient.categorylist(categoryReq.getMerchantId());
		Message message = res.getBody();
//		List<CategoryTree> cate = (List<CategoryTree>) message.getData();
//		System.out.println(cate.size());
		//return new S2ClientResp<>(MessageType., result);
		ArrayList<CategoryTree> list = new ArrayList<CategoryTree>();
		ArrayList<CategoryTree> ca = (ArrayList<CategoryTree>) message.getData();
		list.addAll(ca);
		return new CategoryResp(list);
	}
}
