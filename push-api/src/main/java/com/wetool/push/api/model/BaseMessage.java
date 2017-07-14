package com.wetool.push.api.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 基础消息对象 <br>
 * 长链接通讯的基础传输类型。客户端处理消息类型时，必须选择该类作为处理类型，
 * 不能选择其子类(如：{@link C2ServerReq} 或 {@link S2ClientResp})
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
