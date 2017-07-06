package com.wetool;

import com.wetool.push.api.model.*;
import com.wetool.push.api.model.client.LoginReq;
import com.wetool.push.api.model.client.PingReq;
import com.wetool.push.api.model.server.CategoryMessage;
import com.wetool.push.api.model.server.CommodityResp;
import com.wetool.push.api.model.server.PushMessage;
import com.wetool.push.api.model.server.push.ImageSyncMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

/**
 * netty客户端处理器
 *
 * @author zhangjie
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<BaseMessage> {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case WRITER_IDLE:    // 利用写闲时向服务器发ping消息
                    PingReq pingReq = new PingReq();
                    ctx.writeAndFlush(pingReq);
                    System.out.println("send ping to server …");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, BaseMessage baseMsg) throws Exception {
        MsgType msgType = baseMsg.getType();
        switch (msgType) {
            case RELOGIN_REQ: {
                // 向服务器发起登录
                LoginReq loginReq = new LoginReq();
                channelHandlerContext.writeAndFlush(loginReq);
            }
            break;
            case PING_RESP: {
                System.out.println("receive ping from server …");
            }
            break;
            case PUSH_REQ: {
                PushMessage pushMessage = (PushMessage) baseMsg;
                switch (pushMessage.getPushMsgType()) {

                    case QRCODE_IMAGE_SYNC:
                        ImageSyncMessage ism = (ImageSyncMessage) pushMessage;
                        System.out.println(ism.getImageId());
                        break;
                }
                System.out.println("receive push message from server …");
            }
            break;
            case CATEGORY_REQ:{
            	CategoryMessage cm = (CategoryMessage) baseMsg;
            	System.out.println(cm);
            }
            break;
            case COMMODITY_RESP:{
            	CommodityResp cm = (CommodityResp) baseMsg;
            	System.out.println(cm.getFlag());
            	System.out.println(cm);
            }
            default:
                System.out.println("receive " + msgType.name() + " from server …");
                break;
        }
        ReferenceCountUtil.release(msgType);
    }
}