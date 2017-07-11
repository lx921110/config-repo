package com.wetool.push.api.model.client;

import java.util.Date;

import com.wetool.push.api.model.C2ServerReq;
import com.wetool.push.api.model.MsgType;

import lombok.Getter;
import lombok.Setter;

/**
 * 商品信息同步
 * client —> server
 * @author zhangjie
 */
@Getter
@Setter
public class CommodityReq extends C2ServerReq {
	private static final long serialVersionUID = -4708193591146035124L;

	private String updateDate;//商品更新时间作为条件查询
	
	private Long page;//请求页数
	
	private Long merchantId; //商家ID
	
	private Integer size ; // 查询条数
	
	public CommodityReq() {
        super(MsgType.COMMODITY_REQ);
    }

}