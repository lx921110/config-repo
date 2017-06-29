package com.wetool.push.api.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 基础消息对象
 * @author zhangjie
 */
@Setter
@Getter
public abstract class BaseMessage  implements Serializable {
	private static final long serialVersionUID = -1145254846699760465L;
	
	/** 消息类型 */
	private MsgType type;

    //初始化客户端id
    public BaseMessage(MsgType type) {
        this.type = type;
    }
}
