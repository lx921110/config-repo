package com.wetool.push.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 服务端响应对象
 * server —> client
 * @author zhangjie
 * @param <T>
 */
@Getter
@Setter
public class S2ClientResp<T> extends BaseMessage {
	private static final long serialVersionUID = -3110309144605914943L;
	
	/** 返回对象 */
	private final Result result;
	private T data;
	
	public S2ClientResp(MsgType type, Result result) {
        super(type);
        this.result = result;
    }
}
