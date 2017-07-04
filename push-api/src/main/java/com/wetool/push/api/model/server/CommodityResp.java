package com.wetool.push.api.model.server;

import java.util.List;
import com.wetool.push.api.model.MsgType;
import com.wetool.push.api.model.Result;
import com.wetool.push.api.model.S2ClientResp;
import com.wetool.push.api.model.model.Commodity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommodityResp extends S2ClientResp {
	private static final long serialVersionUID = -3828291867249691076L;
	
	public CommodityResp(MsgType type, Result result) {
		super(type, result);
	}
	
	private Boolean flag; //检索是否还有内容

	public List<Commodity> commoditys; //商品信息集合
}