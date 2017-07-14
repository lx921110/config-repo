package com.wetool.push.api.model.server;

import com.wetool.push.api.model.BaseMessage;
import com.wetool.push.api.model.MsgType;
import com.wetool.push.api.model.PushMsgType;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by zhanbin on 7/1/17.
 * Server --> Client
 * 服务端推送消息类型
 */
@Getter
@Setter
public class PushMessage<T> extends BaseMessage {

    private static final long serialVersionUID = 2781667713166507274L;
    private PushMsgType pushMsgType;

    private T data;
    /**
     * 设备唯一序列号
     */
    private String sn;

    public PushMessage(PushMsgType pushMsgType,String sn,T data) {
        super(MsgType.PUSH_REQ);
        this.pushMsgType = pushMsgType;
        this.sn = sn;
        this.data = data;
    }
}
