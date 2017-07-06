package com.wetool.push.api.model.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 商品拆分规则
 */

@Getter
@Setter
public class SplitCommodityRule implements Serializable {

    private static final long serialVersionUID = 8839680717041453515L;
    private Long id;

    private Long commodityId;// 拆分后商品编号

    private Double splitNumber;// 拆分数量
}