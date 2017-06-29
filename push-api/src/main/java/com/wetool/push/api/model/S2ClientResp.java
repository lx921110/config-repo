package com.wetool.push.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 服务端响应对象
 * server —> client
 * @author zhangjie
 */
@Getter
@Setter
public class S2ClientResp extends BaseMessage {
	private static final long serialVersionUID = -3110309144605914943L;
	
	/** 返回对象 */
	private final Result result;
    
	public S2ClientResp(MsgType type, Result result) {
        super(type);
        this.result = result;
    }
}
