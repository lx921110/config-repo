package com.wetool.push.server.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.wetool.push.api.model.MsgType;
import com.wetool.push.api.model.Result;
import com.wetool.push.api.model.S2ClientResp;
import com.wetool.push.api.model.client.VersionReq;
import com.wetool.push.server.feign.MacFeignClient;
import com.wetool.push.server.model.MacVersion;
import com.wetool.push.server.model.Message;
import feign.Feign;

@Component
public class VersionService {
	
	@Autowired
	Feign.Builder builder;
	
	@Value("${url.mac-server}")
	private String url;
	
	public S2ClientResp save(VersionReq req) {
		String clientId = req.getClientId();
		try {
			MacFeignClient macFeignClient = builder.target(MacFeignClient.class, url);
			MacVersion macVersion = new MacVersion();
			BeanUtils.copyProperties(req, macVersion);	// 属性拷贝
			macVersion.setSn(clientId);
			macVersion.setAdvertVersion("1");
			macVersion.setCashierVersion("1.1");
			macVersion.setFirmwareVersion("1.2");
			macVersion.setPosVersion("1.3");
			macVersion.setShopVersion("1.4");
			ResponseEntity<Message<?>> response = macFeignClient.save(macVersion);
			Message<?> message = response.getBody();
			switch (message.getCode()) {
			case 0: // 成功状态
				System.out.println("客户端【 " + clientId + "】版本同步成功! ");
				return new S2ClientResp(MsgType.VERSION_RESP, Result.SUCCESS);
			case 3: // 未找到设备
				System.out.println("客户端【 " + clientId + "】版本同步失败，未找到设备! ");
				return new S2ClientResp(MsgType.VERSION_RESP, Result.SUCCESS);
			case 4: // 请求参数异常
				System.out.println("客户端【 " + clientId + "】版本同步失败，请求参数异常! ");
				return new S2ClientResp(MsgType.VERSION_RESP, Result.SUCCESS);
			default:	// 其它状态码全部判定为异常
				throw new Exception();
			}
		} catch(Exception e) {
			System.out.println("客户端【 " + clientId + "】版本同步失败! ");
			return new S2ClientResp(MsgType.VERSION_RESP, Result.ERROR);
		}
	}
}
