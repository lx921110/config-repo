package com.wetool;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wetool.codec.NettyMessageDecoder;
import com.wetool.codec.NettyMessageEncoder;
import com.wetool.coder.KryoDecoder;
import com.wetool.coder.KryoEncoder;
import com.wetool.push.api.model.BaseMessage;
import com.wetool.push.api.model.C2ServerReq;
import com.wetool.push.api.model.S2ClientResp;

@Configuration
public class NettyClientConfig {

	private final String host = "192.168.1.91"; // 服务地址
	private final int port = 3333; // 服务端口号
	private final int READ_IDEL_TIME_OUT = 20; // 读超时
	private final int WRITE_IDEL_TIME_OUT = 10; // 写超时
	private final int ALL_IDEL_TIME_OUT = 0;

	@Bean
	public Bootstrap bootstrap() {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.channel(NioSocketChannel.class);
		bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
		bootstrap.group(eventLoopGroup);
		bootstrap.remoteAddress(host, port);
		bootstrap.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel socketChannel) throws Exception {
				socketChannel.pipeline()
						.addLast(new IdleStateHandler(READ_IDEL_TIME_OUT, WRITE_IDEL_TIME_OUT, ALL_IDEL_TIME_OUT));
				socketChannel.pipeline().addLast(new ObjectEncoder());
				socketChannel.pipeline().addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
				socketChannel.pipeline().addLast(new NettyClientHandler());
			}
		});
		return bootstrap;
	}

	@Bean
	public SocketChannel socketChannel() throws InterruptedException {
		SocketChannel socketChannel = null;
		ChannelFuture future = bootstrap().connect(host, port).sync();
		if (future.isSuccess()) {
			socketChannel = (SocketChannel) future.channel();
			System.out.println("connect server  成功---------");
		}
		return socketChannel;
	}
}
