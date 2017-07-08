package com.wetool.push.api.model.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Commodity2 implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6615787722807678766L;

	private Long id;

    private Long merchantId; // 商家ID

    private String name; // 商品名称

    private Long categoryId; // 商品分类标识

    private String barcode; // 商品条形码

    private String pinyin; // 商品名称拼音

    /*private String pinyinShorthand; // 商品名称拼音首字母简写

    private String type; // 商品类型 0:正规商品 1:自编码; 3:称重商品

    private String picPath; // 商品图片路径

    private Resource resources;// 资源列表

    private String specification; // 商品规格

    private String unit; // 商品单位

    private Boolean isDetachable; // 是否可拆分(is开头后台无法接收jsonboolean)

    private String description; // 描述

    private BigDecimal sellingPrice; // 销售价格

    private BigDecimal buyingPrice; // 进货价格

    private Boolean isGs1; // 是否为正规条码商品

    private String country; // 商品所属国家

    private SplitCommodityRule splitRule; // 拆分规则

    private Double warningNumber; // 商品预警数

    private boolean isDeleted; // 逻辑删除标记 0-未删除 1-已删除

    private Double inventoryQuantity; //商家商品库存数
*/}