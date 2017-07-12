/* 
 * Copyright (c) 2017, S.F. Express Inc. All rights reserved.
 */
package com.wetool.push.api.model.server.push;

import com.wetool.push.api.model.PushMsgType;
import com.wetool.push.api.model.server.PushMessage;

import lombok.Getter;

/**
 * 描述：
 * 
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年7月11日      lesim         Create
 * ****************************************************************************
 * </pre>
 * @author lesim
 * @since 1.0
 */
@Getter
public class MeituanPushMessage extends PushMessage<MeituanPushMessage> {

	/**  */
	private static final long serialVersionUID = 3678794666390356787L;
	
	public MeituanPushMessage(PushMsgType pushMsgType, String sn) {
		super(PushMsgType.MEITUAN_ADD_PUSH, sn);
	}

	public Long orderId;
	
	public MeituanPushMessage( String sn,Long orderId) {
		this(PushMsgType.MEITUAN_ADD_PUSH, sn);
		this.orderId = orderId;
	}
}
