package com.wetool.push.server.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by zhanbin on 7/1/17.
 */
public interface PushChannel {

    String INPUT_CHANNEL_NAME="push_input_channel";

    @Input("push_input_channel")
    SubscribableChannel receiveMessageChannel();

/*
    @Output("push_output_channel")
    MessageChannel sendMessageChannel();*/
}
