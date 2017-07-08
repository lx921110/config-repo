package com.wetool.push.server.model;

import com.wetool.push.api.model.model.Commodity;
import com.wetool.push.api.model.model.Resource;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
public class CommodityReceive implements Serializable {
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

    private Resource resources;// 资源列表

    private String specification; // 商品规格

    private String unit; // 商品单位

    private Boolean isDetachable; // 是否可拆分(is开头后台无法接收jsonboolean)

    private BigDecimal sellingPrice; // 销售价格

    private BigDecimal buyingPrice; // 进货价格

    private Boolean isGs1; // 是否为正规条码商品

    public Commodity getCommodity() {
        Commodity c = new Commodity();
        c.setId(this.id);

        c.setMerchantId(merchantId); // 商家ID

        c.setName(name); // 商品名称

        c.setCategoryId(categoryId); // 商品分类标识

        c.setBarcode(barcode); // 商品条形码

        c.setPinyin(pinyin); // 商品名称拼音

        c.setPinyinShorthand(pinyinShorthand); // 商品名称拼音首字母简写

        c.setType(type); // 商品类型 0:正规商品 1:自编码; 3:称重商品

        c.setPicPath(picPath); // 商品图片路径

        c.setResources(resources);// 资源列表

        c.setSpecification(specification); // 商品规格

        c.setUnit(unit); // 商品单位

        c.setIsDetachable(isDetachable); // 是否可拆分(is开头后台无法接收jsonboolean)

        if (sellingPrice != null) {
            c.setSellingPrice(sellingPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString()); // 销售价格
        } else {
            c.setSellingPrice("0");
        }

        if (buyingPrice != null) {
            c.setBuyingPrice(buyingPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString()); // 进货价格
        } else {
            c.setBuyingPrice("0");
        }

        c.setIsGs1(isGs1); // 是否为正规条码商品
        return c;
    }
}