package com.wetool.push.api.model.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 图片资源文件
 *
 * @author zhangjie
 */
@Data
public class Resource implements Serializable {

    private static final long serialVersionUID = 5726047074482003508L;
    /**
     * id（流水号主键）
     */
    private Long id;

    /**
     * 资源路径
     */
    private String resUrl;

    public String getResUrl() {
        return "http://wetool.oss-cn-beijing.aliyuncs.com/" + this.resUrl;
    }

}