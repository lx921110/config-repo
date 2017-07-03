package com.wetool.push.api.model.model;

import java.io.Serializable;
import lombok.Data;

/**
 * 商品拆分规则
 */

@Data
public class SplitCommodityRule implements Serializable {
	private static final long serialVersionUID = 8525410992607003727L;

	private Long id;

	private Long commodityId;// 拆分后商品编号

	private Double splitNumber;// 拆分数量
}