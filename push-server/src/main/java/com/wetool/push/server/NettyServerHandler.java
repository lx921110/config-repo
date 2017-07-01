package com.wetool.push.server;

import com.wetool.push.api.model.C2ServerReq;
import com.wetool.push.api.model.MsgType;
import com.wetool.push.api.model.Result;
import com.wetool.push.api.model.S2ClientResp;
import com.wetool.push.api.model.client.LoginReq;
import com.wetool.push.api.model.client.PingReq;
import com.wetool.push.api.model.client.VersionReq;
import com.wetool.push.api.model.server.ReloginReq;
import com.wetool.push.server.service.CommodityService;
import com.wetool.push.server.service.LoginService;
import com.wetool.push.server.service.VersionService;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<C2ServerReq> {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	VersionService versionService;
	
	@Autowired
	CommodityService commodityService;
	
	/** 通道失效处理 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		/* 当通道失效时，从通道列表中移除 */
		NettyChannelMap.remove((SocketChannel) ctx.channel());
	}

	/** 消息接收 */
	@Override
	protected void messageReceived(ChannelHandlerContext channelHandlerContext, C2ServerReq c2ServerReq) throws Exception {

		if (MsgType.LOGIN_REQ.equals(c2ServerReq.getType())) {
			LoginReq loginMsg = (LoginReq) c2ServerReq;
			String clientId = loginMsg.getClientId();	//客户端ID（设备SN）
			String token = loginMsg.getToken();	// 令牌
			ContextHolder.set(token);
			
			System.out.println("客户端[" + loginMsg.getClientId() + "] token=" + token);
			S2ClientResp resp = loginService.login(clientId);
			if (Result.SUCCESS == resp.getResult()) {	// 如果登录成功
				// 当前客户端通道加入通道集合
				NettyChannelMap.add(clientId, (SocketChannel) channelHandlerContext.channel());
			}
			/* 返回响应消息 */
			NettyChannelMap.get(loginMsg.getClientId()).writeAndFlush(resp);
		} else {
			/* 未登录或连接中断，服务器要求客户端重新登录 */
			if (NettyChannelMap.get(c2ServerReq.getClientId()) == null) {
				ReloginReq reloginReq = new ReloginReq();
				channelHandlerContext.channel().writeAndFlush(reloginReq);
			}
		}
		
		switch (c2ServerReq.getType()) {
		case PING_REQ: {
			PingReq pingReq = (PingReq) c2ServerReq;
			S2ClientResp s2ClientResp = new S2ClientResp(MsgType.PING_RESP, Result.SUCCESS);
			NettyChannelMap.get(pingReq.getClientId()).writeAndFlush(s2ClientResp);
		}
			break;
		case VERSION_REQ: {	// 版本同步请求
			VersionReq versionReq = (VersionReq) c2ServerReq;
			S2ClientResp s2ClientResp = versionService.save(versionReq);
			NettyChannelMap.get(versionReq.getClientId()).writeAndFlush(s2ClientResp);
		}
			break;
		default:
			break;
		}
		ReferenceCountUtil.release(c2ServerReq);
	}



}