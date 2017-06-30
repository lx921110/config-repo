package com.wetool.push.server.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MacVersion implements Serializable {
	private static final long serialVersionUID = 6381840232661167808L;

	/** 设备编号 */
	private String sn;
	
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
