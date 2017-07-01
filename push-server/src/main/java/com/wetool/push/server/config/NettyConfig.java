package com.wetool.push.server.config;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.wetool.push.server.NettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;

@Configuration
@Sharable
public class NettyConfig {
	
	@Value("${netty.port}")
	private int port;  //服务端口号
	@Value("${netty.read.idel.timeout}")
	private int READ_IDEL_TIME_OUT;  // 读超时
	@Value("${netty.write.idel.timeout}")
	private int WRITE_IDEL_TIME_OUT; // 写超时
	@Value("${netty.all.idel.timeout}")
	private int ALL_IDEL_TIME_OUT;
	
	@Autowired
	NettyServerHandler nettyServerHandler;
	
    @Bean
    public ApplicationListener<ContextRefreshedEvent> applicationListener() {
    	return event -> serverBootstrap();
    }
    
	@Bean
	public ChannelInitializer<SocketChannel> channelInitializer() {
		return new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel socketChannel) throws Exception {
				ChannelPipeline p = socketChannel.pipeline();
				p.addLast(new ObjectEncoder());
				p.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
				p.addLast(new IdleStateHandler(READ_IDEL_TIME_OUT, WRITE_IDEL_TIME_OUT, ALL_IDEL_TIME_OUT, TimeUnit.SECONDS));
				p.addLast(nettyServerHandler);
			}
		};
	}
	
    private void serverBootstrap() {
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(boss, worker);
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.option(ChannelOption.SO_BACKLOG, 128);
		bootstrap.option(ChannelOption.TCP_NODELAY, true);	// 通过NoDelay禁用Nagle,使消息立即发出去，不用等待到一定的数据量才发出去
		bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);	// 保持长连接状态
		bootstrap.childHandler(channelInitializer());
		try {
			ChannelFuture f = bootstrap.bind(port).sync();
			if (f.isSuccess()) {
				System.out.println("消息推送服务启动	监听端口[{" + port + "}]");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
