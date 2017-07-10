package com.wetool.push.api.model.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 图片资源文件
 */
@Getter
@Setter
public class Resource implements Serializable {

	private static final long serialVersionUID = -5949216842396501491L;

	/** id（流水号主键） */
	private Long id;

	/**
	 * 资源路径
	 */
	private String resUrl;

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	public String getResUrl() {
		return "http://wetool.oss-cn-beijing.aliyuncs.com/"+this.resUrl;
	}

}