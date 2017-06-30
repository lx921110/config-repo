package com.wetool.push.api.model.server;

import com.wetool.push.api.model.MsgType;
import com.wetool.push.api.model.Result;
import com.wetool.push.api.model.S2ClientResp;
import lombok.Data;

@Data
public class CommodityResp extends S2ClientResp{
	private static final long serialVersionUID = -3828291867249691076L;
	
	public CommodityResp(MsgType type, Result result) {
		super(type, result);
	}
	
	
}
