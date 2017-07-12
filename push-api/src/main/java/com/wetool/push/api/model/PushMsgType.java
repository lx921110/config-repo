package com.wetool.push.api.model;

/**
 * Created by zhanbin on 7/1/17.
 * Server --> Client
 * 推送消息类型
 */
public enum PushMsgType {
    /**
     * 二维码扫码图片同步消息
     */
    QRCODE_IMAGE_SYNC,
    /**
     * 支付状态同步
     */
    PAYMENT_STATUS_SYNC,
    /**
     * 美团订单创建
     */
    MEITUAN_ADD_PUSH
}
