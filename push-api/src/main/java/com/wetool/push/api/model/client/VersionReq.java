package com.wetool.push.api.model.client;

import com.wetool.push.api.model.C2ServerReq;
import com.wetool.push.api.model.MsgType;

import lombok.Getter;
import lombok.Setter;

/**
 * 版本信息同步
 * client —> server
 * @author zhangjie
 */
@Getter
@Setter
public class VersionReq extends C2ServerReq {
	private static final long serialVersionUID = -4708193591146035124L;

	/** 商家端版本号 */
	private String shopVersion;
	
	/** 收银端版本号 */
	private String cashierVersion;
	
	/** 广告端版本号 */
	private String advertVersion;
	
	/** pos service版本号 */
	private String posVersion;
	
	/** 固件版本号 */
	private String firmwareVersion;
	
	public VersionReq() {
        super(MsgType.VERSION_REQ);
    }
}
