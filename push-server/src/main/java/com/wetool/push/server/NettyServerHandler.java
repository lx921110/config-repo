package com.wetool.push.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.wetool.push.api.model.C2ServerReq;
import com.wetool.push.api.model.MsgType;
import com.wetool.push.api.model.client.LoginReq;
import com.wetool.push.api.model.client.PingReq;
import com.wetool.push.api.model.client.VersionReq;
import com.wetool.push.api.model.server.PingResp;
import com.wetool.push.api.model.server.ReloginReq;
import com.wetool.push.server.NettyChannelMap;
import com.wetool.push.server.service.CommodityService;
import com.wetool.push.server.service.LoginService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;

@Component
public class NettyServerHandler extends SimpleChannelInboundHandler<C2ServerReq> {
	
	@Autowired
	LoginService loginService;
	
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
			String token = loginMsg.getToken();
			ContextHolder.set(token);
			System.out.println("客户端[" + loginMsg.getClientId() + "] token=" + token);
//			LoginService loginService = new LoginService();
			loginService.login();
			if (true) {
				// 登录成功,把channel存到服务端的map中
				NettyChannelMap.add(loginMsg.getClientId(), (SocketChannel) channelHandlerContext.channel());
				System.out.println("client" + loginMsg.getClientId() + " 登录成功");
			}
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
			PingResp pingResp = new PingResp();
			NettyChannelMap.get(pingReq.getClientId()).writeAndFlush(pingResp);
		}
			break;
		case VERSION_REQ: {	// 版本同步请求
			VersionReq versionReq = (VersionReq) c2ServerReq;
			String token = versionReq.getToken();
			if (true) { // 令牌验证
				System.out.println("客户端[" + versionReq.getClientId() + "，商家端版本：" + versionReq.getShopVersion());
			}
		}
			break;
		default:
			break;
		}
		ReferenceCountUtil.release(c2ServerReq);
	}
}