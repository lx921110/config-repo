package com.wetool.push.api.model.server;

import java.util.List;

import com.wetool.push.api.model.BaseMessage;
import com.wetool.push.api.model.MsgType;
import com.wetool.push.api.model.model.CategoryTree;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResp extends BaseMessage{
	private static final long serialVersionUID = -856292023483107158L;

	private List<CategoryTree> categorys;
	
	public CategoryResp(List<CategoryTree> categorys) {
		super(MsgType.CATEGORY_RESP);
		this.categorys = categorys;
	}

}
