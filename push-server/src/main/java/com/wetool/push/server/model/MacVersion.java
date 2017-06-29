package com.wetool.push.server.model;

import lombok.Data;

@Data
public class MacVersion {
	
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
}
