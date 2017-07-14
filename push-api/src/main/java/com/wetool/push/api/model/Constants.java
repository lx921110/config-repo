package com.wetool.push.api.model;

public class Constants {
	
    private static String clientId;
    private static String token;
    
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