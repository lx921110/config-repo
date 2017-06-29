package com.wetool.push.api.model.server;

import com.wetool.push.api.model.BaseMessage;
import com.wetool.push.api.model.MsgType;

/**
 * Ping响应
 * server —> client
 * @author zhangjie
 */
public class PingResp extends BaseMessage {
	private static final long serialVersionUID = 1873822383547968656L;

	public PingResp() {
        super(MsgType.PING_RESP);
    }
}