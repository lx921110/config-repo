package com.wetool.push.api.model.model;

import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 商品拆分规则
 */

@Getter
@Setter
public class SplitCommodityRule {

	private Long id;

	private Long commodityId;// 拆分后商品编号

	private Double splitNumber;// 拆分数量
}