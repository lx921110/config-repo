package com.wetool.push.api.model.server;

import com.wetool.push.api.model.BaseMessage;
import com.wetool.push.api.model.MsgType;

import lombok.Getter;
import lombok.Setter;

/**
 * 重新登录请求
 * server —> client
 * @author zhangjie
 */
@Setter
@Getter
public class ReloginReq extends BaseMessage {
	private static final long serialVersionUID = 8275791167051070676L;

	public ReloginReq() {
        super(MsgType.RELOGIN_REQ);
    }
}