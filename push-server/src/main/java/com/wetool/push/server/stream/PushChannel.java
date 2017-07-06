package com.wetool.push.server.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by zhanbin on 7/1/17.
 */
public interface PushChannel {

    String INPUT_CHANNEL_NAME = "push_input_channel";
    String OUTPUT_CHANNEL_NAME = "push_output_channel";

    @Input(PushChannel.INPUT_CHANNEL_NAME)
    SubscribableChannel receiveMessageChannel();

/*
    @Output(PushChannel.OUTPUT_CHANNEL_NAME)
    MessageChannel sendMessageChannel();*/
}
