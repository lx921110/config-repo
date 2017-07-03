package com.wetool.push.server.service;

import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.wetool.push.api.model.MsgType;
import com.wetool.push.api.model.Result;
import com.wetool.push.api.model.S2ClientResp;
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
	
	//@Value("${url.commodity-server}")
	private String url;
	
	public S2ClientResp commSync(CommodityReq commodityReq) throws Exception {
		try {
			url = "http://127.0.0.1:16010";
			Boolean flag = true;
			/* 商品获取接口 */
			CommodityFeignClient commodityFeignClient = builder.target(CommodityFeignClient.class, url);
			/* 获取接口返回数据 */
			ResponseEntity<Message<?>> resp = commodityFeignClient.list(commodityReq.getUpdateDate(), commodityReq.getMerchantId(),
					commodityReq.getSize());
			System.out.println(resp.getBody());
			Message message = resp.getBody();
			/* 数据对象 */
			LinkedHashMap obj = (LinkedHashMap) message.getData();
			/* 商品信息数据集合 */
			List<Commodity> commoditys = (List) obj.get("content");
			/* 获取分页对象 */
			LinkedHashMap pages = (LinkedHashMap) obj.get("page");
			/* 获取商品信息提啊件查询总记录数 */
			Integer totalElements = (Integer) pages.get("totalElements");
			/* 判断是否获取全部查询信息 */
			if (commodityReq.getSize() < totalElements) {
				flag = false;
			}
			CommodityResp commodityResp = null;
			switch (message.getCode()) {
			case 0: // 成功
				commodityResp = new CommodityResp(MsgType.COMMODITY_RESP, Result.SUCCESS);
				commodityResp.setCommoditys(commoditys);
				commodityResp.setFlag(flag);
				System.out.println("返回客户端------->"+commodityResp);
				return commodityResp;
			case 1:// 操作失败
				System.out.println("商品信息查询【 " + commodityReq.getSize() + "】条数据失败！ ");
				return new S2ClientResp(MsgType.COMMODITY_RESP, Result.SUCCESS);
			case 19: // 商家未登录
				System.out.println("商品信息同步【 " + commodityReq.getSize() + "】条数据失败！，商家未登录！ ");
				return new S2ClientResp(MsgType.COMMODITY_RESP, Result.SUCCESS);
			case 18: // 时间格式错误
				System.out.println("商品信息同步【 " + commodityReq.getSize() + "】条数据失败！，商家时间格式错误！ ");
				return new S2ClientResp(MsgType.COMMODITY_RESP, Result.SUCCESS);
			default:
				throw new Exception();
			}
		}catch (Exception e) {
			System.out.println("商品信息同步【 " + commodityReq.getSize() + "】条数据失败！ ");
			return new S2ClientResp(MsgType.COMMODITY_RESP, Result.SUCCESS);
		}
	}
}