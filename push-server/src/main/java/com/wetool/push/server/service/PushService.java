package com.wetool.push.server.service;

import com.wetool.push.api.model.server.PushMessage;
import com.wetool.push.server.NettyChannelMap;
import com.wetool.push.server.stream.PushChannel;
import io.netty.channel.Channel;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * Created by zhanbin on 7/1/17.
 * 服务端推送服务
 */
@EnableBinding(PushChannel.class)
public class PushService {

    @StreamListener(value = PushChannel.INPUT_CHANNEL_NAME)
    public void handleMessage(PushMessage pushMessage) {
//        switch (pushMessage.getPushMsgType()) {
//            case QRCODE_IMAGE_SYNC:
//                ImageSyncMessage imageSyncMessage = (ImageSyncMessage) pushMessage;
//                NettyChannelMap.get(imageSyncMessage.getSn()).writeAndFlush(pushMessage);
//                break;
//        }
        this.sendMessageToClient(pushMessage);
    }

    /**
     * 发送消息到客户端
     */
    private void sendMessageToClient(PushMessage pushMessage) {

        Channel client = NettyChannelMap.get(pushMessage.getSn());
        if (client != null) {
            client.writeAndFlush(pushMessage);
        }
        //TODO 重发
    }
}
