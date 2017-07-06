package com.wetool.push.server.service;

import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.wetool.push.api.model.MsgType;
import com.wetool.push.api.model.Result;
import com.wetool.push.api.model.S2ClientResp;
import com.wetool.push.server.ContextHolder;
import com.wetool.push.server.feign.LoginFeignClient;
import feign.Feign;

@Component
public class LoginService {
	
	@Autowired
	Feign.Builder builder;
	
	@Value("${url.auth-server}")
	private String url;
	
	public S2ClientResp login(String clientId) {
		try {
			LoginFeignClient authFeignClient = builder.target(LoginFeignClient.class, url);
			ResponseEntity<Map<String, Object>> response = authFeignClient.checkToken(ContextHolder.get());
			Map<String, Object> body = response.getBody();
			String userName = String.valueOf(body.get("user_name"));
			if(!StringUtils.isEmpty(userName)) {
				System.out.println("客户端【 " + clientId + "】登录成功! ");
				return new S2ClientResp(MsgType.LOGIN_RESP, Result.SUCCESS);
			} else {
				throw new Exception();
			}
		} catch(Exception e) {
			System.out.println("客户端【 " + clientId + "】登录失败! ");
			return new S2ClientResp(MsgType.LOGIN_RESP, Result.ERROR);
		}
	}
}