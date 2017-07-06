package com.wetool.push.api.model.server;

import com.wetool.push.api.model.BaseMessage;
import com.wetool.push.api.model.MsgType;
import com.wetool.push.api.model.model.CategoryTree;

import lombok.Getter;

@Getter
public class CategoryMessage extends BaseMessage{
	private static final long serialVersionUID = 9210666482008350384L;

	private CategoryTree categoryTree;
	
	public CategoryMessage(MsgType type) {
		super(MsgType.CATEGORY_REQ);
		
	}

}
