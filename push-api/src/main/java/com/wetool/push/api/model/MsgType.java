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
    
    /** 商品信息同步请求 */
    COMMODITY_REQ ,
    
    /** ============消息类型（服务端 -> 客户端） */
	/** 登录响应 */
    LOGIN_RESP,
    
    /** 重新登录请求 */
	RELOGIN_REQ,

    /**
     * 推送服务
     */
    PUSH_REQ,
	
	 /** ping响应 */
	PING_RESP,
	
	 /** 商品信息同步响应 */
    COMMODITY_RESP ,
    
    /** 版本同步响应 */
    VERSION_RESP
}