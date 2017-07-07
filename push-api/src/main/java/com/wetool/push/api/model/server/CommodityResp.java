package com.wetool.push.api.model.server;

import java.io.Serializable;
import java.util.List;

import com.wetool.push.api.model.BaseMessage;
import com.wetool.push.api.model.MsgType;
import com.wetool.push.api.model.model.Commodity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommodityResp extends BaseMessage implements Serializable {
	public CommodityResp(MsgType type) {
		super(type);
	}

	private static final long serialVersionUID = 2727427364204442836L;

	private Boolean flag; //检索是否还有内容

    public List<Commodity> commoditys; //商品信息集合

}