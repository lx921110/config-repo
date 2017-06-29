package com.wetool.push.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 客户端请求对象
 * client —> server
 * @author zhangjie
 */
@Getter
@Setter
public abstract class C2ServerReq extends BaseMessage {
	private static final long serialVersionUID = 7625369671957766529L;

	/** 令牌 */
	private final String token;
	
	/** 客户端ID（设备SN编号） */
    private final String clientId;
    
	public C2ServerReq(MsgType type) {
        super(type);
        this.token = Constants.getToken();
        this.clientId = Constants.getClientId();
    }
   
}
