package com.wetool.push.server.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${url.commodity-server}")
	private String url;
	
	public CategoryResp categorySync(CategoryReq categoryReq){
		/* 商品获取接口 */
		CommodityFeignClient commodityFeignClient = builder.target(CommodityFeignClient.class, url);
		ResponseEntity<Message<ArrayList<CategoryTree>>> res = commodityFeignClient.categorylist(categoryReq.getMerchantId());
		Message<ArrayList<CategoryTree>> message = res.getBody();
		ArrayList<CategoryTree> list = new ArrayList<CategoryTree>();
		ArrayList<CategoryTree> ca = message.getData();
		switch (message.getCode()) {
		case 0:
			System.out.println("商品分类同步成功");
			list.addAll(ca);
			break;
		case 1:
			System.out.println("商品分类同步失败");
			list.add(null);
			break;
		case 19:
			System.out.println("商家未登录");
			list.add(null);
			break;
		case 20:
			System.out.println("商品分类未找到");
			list.add(null);
			break;
		default:
			break;
		}
		return new CategoryResp(list);
	}
}
