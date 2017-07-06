package com.wetool.push.api.model.model;

import java.util.List;
import com.wetool.push.api.model.BaseMessage;
import com.wetool.push.api.model.MsgType;
import lombok.Getter;
import lombok.Setter;

/**
 * 商品分类树结构数据（Json返回对象）
 * @author zhangjie
 */
@Getter
@Setter
public class CategoryTree {
	
	private Long value;
	private String label;
	private Long parent;
	private boolean isLeaf;
	
//	@JsonInclude(Include.NON_NULL)
	private List<CategoryTree> children;
	
}