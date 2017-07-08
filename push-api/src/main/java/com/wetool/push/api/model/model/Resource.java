package com.wetool.push.api.model.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 图片资源文件
 * 
 * @author zhangjie
 */
@Getter
@Setter
public class Resource implements Serializable{

	private static final long serialVersionUID = 5726047074482003508L;
	/** id（流水号主键） */
	private Long id;

	/**
	 * 资源路径
	 */
	private String resUrl;
	
	public String getResUrl() {
		return "http://wetool.oss-cn-beijing.aliyuncs.com/"+this.resUrl;
	}

}