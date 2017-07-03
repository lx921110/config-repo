package com.wetool.push.api.model.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 图片资源文件
 * 
 * @author zhangjie
 */
@Data
public class Resource implements Serializable {

	private static final long serialVersionUID = -5949216842396501491L;

	/** id（流水号主键） */
	private Long id;


	/**
	 * 资源类型 0-图片 1-视频
	 */
	private int type;

	/**
	 * 资源路径
	 */
	private String resUrl;
	
	public String getResUrl() {
		return "http://wetool.oss-cn-beijing.aliyuncs.com/"+this.resUrl;
	}

	/**
	 * 图片宽度
	 */
	private float width;

	/**
	 * 图片高度
	 */
	private float heigth;

}