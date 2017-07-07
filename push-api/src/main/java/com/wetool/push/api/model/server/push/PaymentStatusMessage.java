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
    private long shopId;
    private String status;
    private String statusCode;

    public PaymentStatusMessage(String sn, String orderNo, String orderType, long shopId, String status, String statusCode) {
        super(PushMsgType.PAYMENT_STATUS_SYNC, sn);
        this.orderNo = orderNo;
        this.orderType = orderType;
        this.shopId = shopId;
        this.status = status;
        this.statusCode = statusCode;
    }
}
