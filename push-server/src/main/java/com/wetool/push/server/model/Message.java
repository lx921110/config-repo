package com.wetool.push.server.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.Getter;

/**
 * 描述：消息对象
 * 
 * @author zhangjie
 * @since 1.0
 */
@Data
public class Message<T> implements Serializable {
	
	private static final long serialVersionUID = 2953946557905707053L;

	private int code;
	
	private String message;
	
	@JsonInclude(Include.NON_NULL)
	private T data;
	
	public Message() {	
	}
	
	public Message(Result result) {
		code = result.getCode();
		message = result.getMessage();
	}
	
	public Message(Result result, String msg) {
		code = result.getCode();
		message = msg;
	}
	
	public Message(Result result, T data) {
		code = result.getCode();
		message = result.getMessage();
		this.data = data;
	}
	
	@Getter
	public enum Result {
		SUCCESS(0, "操作成功！"),
		ERROR(1, "操作失败！"),
		PARAM_ERROR(2, "请求参数格式错误！"),
		CAPTCHA_SEND_AGAIN(3, "验证码短时间内禁止重复发送！"),
		CAPTCHA_FAILED(4, "验证码校验失败！"),
		ACCESS_DENIED(5, "无权访问！"),
		MERCHANT_NOT_FOUND(6, "商家不存在！"),
		ACCOUNT_EXISTED(7, "帐号已存在！"),
		ACCOUNT_NOT_FOUND(8, "帐号不存在！"),
		MAC_NOT_FOUND(9, "设备不存在！"),
		MERCHANT_STATUS_ERROR(10,"商家状态异常"),
		PWD_VERIFY_FAILED(11,"密码错误"),
		MAC_NOT_BIND(12,"设备未绑定店铺"),
		MOBILE_VERIFY_FAILED(13,"手机号与设备号所属店铺手机不匹配"),
		LOGIN_FAILED(14,"登录失败，帐号或密码错误");
		
		private final int code;
		
		private final String message;

		Result(int code, String message) {
			this.code = code;
			this.message = message;
		}
	}
}