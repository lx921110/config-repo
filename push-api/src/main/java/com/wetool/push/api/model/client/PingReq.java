package com.wetool.push.api.model.client;

import com.wetool.push.api.model.C2ServerReq;
import com.wetool.push.api.model.MsgType;

/**
 * Ping消息
 * client —> server
 * @author zhangjie
 */
public class PingReq extends C2ServerReq {
	private static final long serialVersionUID = 9222464933049846726L;

	public PingReq() {
        super(MsgType.PING_REQ);
    }
}