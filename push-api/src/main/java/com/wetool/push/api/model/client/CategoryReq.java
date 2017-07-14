package com.wetool.push.api.model.client;

import com.wetool.push.api.model.C2ServerReq;
import com.wetool.push.api.model.MsgType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryReq extends C2ServerReq {
	private static final long serialVersionUID = 8358586602657358592L;
	
	private Long merchantId;

	public CategoryReq() {
		super(MsgType.CATEGORY_REQ);
	}

}
