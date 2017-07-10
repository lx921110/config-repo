package com.wetool.push.api.model;

/**
 * 消息类型
 *
 * @author zhangjie
 */
public enum MsgType {

    /** ============消息类型（客户端 -> 服务端） */
    /**
     * 登录请求
     * Client - > Server
     */
    LOGIN_REQ,

    /**
     * Ping请求
     * Client - > Server
     */
    PING_REQ,

    /**
     * 版本同步请求
     * Client - > Server
     */
    VERSION_REQ,

    /**
     * 商品信息同步请求
     * Client - > Server
     */
    COMMODITY_REQ,

    /**
     * 商品分类信息同步请求
     * Client - > Server
     */
    CATEGORY_REQ,

    /** ============消息类型（服务端 -> 客户端） */
    /**
     * 登录响应
     * Server - > Client
     */
    LOGIN_RESP,

    /**
     * 重新登录请求
     * Server - > Client
     */
    RELOGIN_REQ,

    /**
     * 推送服务
     * Server - > Client
     */
    PUSH_REQ,

    /**
     * ping响应
     * Server - > Client
     */
    PING_RESP,

    /**
     * 商品信息同步响应
     * Server - > Client
     */
    COMMODITY_RESP,

    /**
     * 商品分类信息同步响应
     */
    CATEGORY_RESP,

    /**
     * 版本同步响应
     * Server - > Client
     */
    VERSION_RESP
}