package com.wetool.push.api.model.server;

import java.io.Serializable;
import java.util.List;
import com.wetool.push.api.model.model.Commodity;

import lombok.Data;

@Data
public class CommodityResp implements Serializable {
	private static final long serialVersionUID = -3828291867249691076L;
		
	private Boolean flag; //检索是否还有内容

	public List<Commodity> commoditys; //商品信息集合
}