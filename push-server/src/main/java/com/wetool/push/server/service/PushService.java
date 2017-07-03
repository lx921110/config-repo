package com.wetool.push.server.service;

import com.wetool.push.api.model.server.PushMessage;
import com.wetool.push.api.model.server.push.ImageSyncMessage;
import com.wetool.push.server.NettyChannelMap;
import com.wetool.push.server.stream.PushChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * Created by zhanbin on 7/1/17.
 * 服务端推送服务
 */
@EnableBinding(PushChannel.class)
public class PushService {

    @StreamListener(PushChannel.INPUT_CHANNEL_NAME)
    public void handleMessage(PushMessage pushMessage) {
        switch (pushMessage.getPushMsgType()) {
            case QRCODE_IMAGE_SYNC:
                ImageSyncMessage imageSyncMessage= (ImageSyncMessage) pushMessage;
                NettyChannelMap.get(imageSyncMessage.getSn()).writeAndFlush(pushMessage);
                break;
        }
    }
}
