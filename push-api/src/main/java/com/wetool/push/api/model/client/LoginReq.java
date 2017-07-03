package com.wetool.push.api.model.client;

import com.wetool.push.api.model.C2ServerReq;
import com.wetool.push.api.model.MsgType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginReq extends C2ServerReq {
	private static final long serialVersionUID = 2972840063622659790L;

	private String username;

    private String password;
    
    public LoginReq() {
        super(MsgType.LOGIN_REQ);
    }
}