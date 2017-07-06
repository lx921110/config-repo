package com.wetool.push.api.model.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * 商品类别
 * 
 * @author zhangjie
 */
@Getter
@Setter
public class Category implements Serializable {
	private static final long serialVersionUID = -1679572259923023796L;

    private Long id; // 主键

    private Long merchantId; //商家Id
    
    private String categoryName; // 目录名称

    private int categoryLevel; // 级别

    private String orderNum; // 排序编号

    private boolean isLeaf; // 是否为叶子节点
    
	private boolean isDeleted; // 逻辑删除标记 0-未删除 1-已删除
    
    private Category parent;
    
    private List<Category> children = new ArrayList<Category>();
}