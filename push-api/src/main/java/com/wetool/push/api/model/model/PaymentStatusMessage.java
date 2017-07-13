package com.wetool.push.api.model.model;

import lombok.Data;

/**
 * Created by zhanbin on 7/13/17.
 */
@Data
public class PaymentStatusMessage {
    private String orderNo;
    private String orderType;
    private String status;
    private String statusCode;

    public PaymentStatusMessage(String orderNo, String orderType, String status, String statusCode) {
        this.orderNo = orderNo;
        this.orderType = orderType;
        this.status = status;
        this.statusCode = statusCode;
    }
}
