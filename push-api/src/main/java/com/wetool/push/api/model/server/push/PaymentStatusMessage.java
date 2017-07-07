package com.wetool.push.api.model.server.push;

import com.wetool.push.api.model.PushMsgType;
import com.wetool.push.api.model.server.PushMessage;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by zhanbin on 7/7/17.
 */
@Getter
@Setter
public class PaymentStatusMessage extends PushMessage {

    private static final long serialVersionUID = -2358907654287814L;
    private String orderNo;
    private String orderType;
    private String shopId;
    private String status;
    private String statusCode;
    private String message;

    public PaymentStatusMessage(PushMsgType pushMsgType, String sn) {
        super(pushMsgType, sn);
    }
}
