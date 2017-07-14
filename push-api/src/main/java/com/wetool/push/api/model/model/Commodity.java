package com.wetool.push.api.model.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Commodity implements Serializable {
    private static final long serialVersionUID = 7638029644952868046L;
    private Long id;

    private Long merchantId; // 商家ID

    private String name; // 商品名称

    private Long categoryId; // 商品分类标识

    private String barcode; // 商品条形码

    private String pinyin; // 商品名称拼音

    private String pinyinShorthand; // 商品名称拼音首字母简写

    private String type; // 商品类型 0:正规商品 1:自编码; 3:称重商品

    private String picPath; // 商品图片路径

    private String specification; // 商品规格

    private String unit; // 商品单位

    private Boolean isDetachable; // 是否可拆分(is开头后台无法接收jsonboolean)

    private String sellingPrice; // 销售价格

    private String buyingPrice; // 进货价格

    private Boolean isGs1; // 是否为正规条码商品

}