package com.wetool.push.api.model;

/**
 * 消息类型
 * @author zhangjie
 */
public enum  MsgType {
	
	/** ============消息类型（客户端 -> 服务端） */
	/** 登录请求 */
	LOGIN_REQ,
	
	/** Ping请求 */
    PING_REQ,
    
    /** 版本同步请求 */
    VERSION_REQ	,
    
    /** ============消息类型（服务端 -> 客户端） */
    /** 重新登录请求 */
	RELOGIN_REQ,
	
	 /** ping响应 */
    PING_RESP
}