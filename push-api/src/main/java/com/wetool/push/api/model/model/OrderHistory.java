/* 
 * Copyright (c) 2017, W.T Express Inc. All rights reserved.
 */
package com.wetool.push.api.model.model;

import java.util.Date;

import lombok.Data;

/**
 * 描述：线上订单扭转状态
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年7月5日      lesim         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author lesim
 * @since 1.0
 */
@Data 
public class OrderHistory {
	private Long id;
	/**
	 * 订单状态   1:等待商家接单   2:商家已拒单    3:商家已接单  4:已完成  5:申请取消    6:取消成功    7:取消失败
	 *  订单扭转状态新加美团状态变更： 8 发起退款，9 确认退款 ，10 驳回退款 ，11 用户取消退款申请， 12 取消退款申诉,\
	 *   配送单发往配送  -->20
	 *   配送单已确认 -->21
	 *   骑手已取餐-->22
	 *   骑手已送达-->23
	 *   配送单已取消-->24
	 */
	private Byte orderStatus;
	/**
	 * 订单扭转信息
	 */
	private String orderMsg;
	private Date createDate;
	private Long orderId;
}
