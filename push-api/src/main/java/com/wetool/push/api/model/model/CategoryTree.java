package com.wetool.push.api.model.model;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 商品分类树结构数据（Json返回对象）
 * @author zhangjie
 */
@Data
public class CategoryTree implements Serializable{
	private static final long serialVersionUID = 3655070767116340724L;
	
	private Long value;
	private String label;
	private Long parent;
	private boolean isLeaf;
	
//	@JsonInclude(Include.NON_NULL)
	private List<CategoryTree> children;
	
	public CategoryTree(Long value, String label, Long parent, boolean isLeaf) {
		this.value = value;
		this.label = label;
		this.isLeaf = isLeaf;
		this.parent = parent;
	}
}