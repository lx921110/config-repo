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
public abstract class PushMessage extends BaseMessage {

    private static final long serialVersionUID = 2781667713166507274L;
    private PushMsgType pushMsgType;

    public PushMessage(PushMsgType pushMsgType) {
        super(MsgType.PUSH_REQ);
        this.pushMsgType = pushMsgType;
    }

}
