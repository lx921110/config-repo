package com.wetool.push.api.model.model;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 商品分类树结构数据（Json返回对象）
 * @author zhangjie
 */
@Getter
@Setter
@ToString
public class CategoryTree implements Serializable {
	private static final long serialVersionUID = -2565160515726603404L;
	
	private Long value;
	private String label;
	private Long parent;
	private boolean isLeaf;
	
//	@JsonInclude(Include.NON_NULL)
	private List<CategoryTree> children;
	
}