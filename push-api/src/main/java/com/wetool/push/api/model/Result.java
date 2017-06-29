package com.wetool.push.api.model;

/**
 * 响应结果枚举
 * @author zhangjie
 */
public enum Result {
	SUCCESS(0, "操作成功！"),
	ERROR(1, "操作失败！");
	
	private final int code;
	private final String message;

	Result(int code, String message) {
		this.code = code;
		this.message = message;
	}
}