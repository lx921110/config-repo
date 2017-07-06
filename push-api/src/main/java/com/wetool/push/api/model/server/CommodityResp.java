package com.wetool.push.api.model.server;

import java.util.List;

import com.wetool.push.api.model.BaseMessage;
import com.wetool.push.api.model.MsgType;
import com.wetool.push.api.model.model.Commodity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommodityResp extends BaseMessage {

    private Boolean flag; //检索是否还有内容

    public Commodity[] commoditys; //商品信息集合

    public CommodityResp() {
        super(MsgType.COMMODITY_RESP);
    }
}