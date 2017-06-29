package com.wetool.push.api.model;

/**
 * 常量类
 * @author zhangjie
 */
public class Constants {
	
    private static String clientId;		// 客户端ID
    private static String token;		// 令牌
    
    public static String getClientId() {
        return clientId;
    }

    public static void setClientId(String clientId) {
        Constants.clientId = clientId;
    }

	public static String getToken() {
		return token;
	}

	public static void setToken(String token) {
		Constants.token = token;
	}
}