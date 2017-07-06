package com.wetool.push.api.model.server;

import com.wetool.push.api.model.BaseMessage;
import com.wetool.push.api.model.MsgType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommodityResp extends BaseMessage {

    private Boolean flag; //检索是否还有内容

    //public List<Commodity> commoditys; //商品信息集合

    public CommodityResp() {
        super(MsgType.COMMODITY_RESP);
    }
}