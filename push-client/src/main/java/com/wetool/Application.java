package com.wetool;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.wetool.push.api.model.Constants;
import com.wetool.push.api.model.client.LoginReq;
import com.wetool.push.api.model.client.VersionReq;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.socket.SocketChannel;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	private final String host = "192.168.1.110"; // 服务地址
	private final int port = 3333; // 服务端口号
	
	@Autowired
	private Bootstrap bootstrap;
	
	@Autowired
	private SocketChannel socketChannel;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) {
		Constants.setClientId("17063000010001");
		Constants.setToken("8611c2b6-7e11-460b-bc88-43f5d401e841");
		
        LoginReq loginMsg=new LoginReq();
        socketChannel.writeAndFlush(loginMsg);
        while (true){
        	try {
        		if(!socketChannel.isActive()) {	// 如果通道断开
            		reconnect();	// 重新连接
            	} else {
            		TimeUnit.SECONDS.sleep(3);
                    VersionReq versionReq=new VersionReq();
                    versionReq.setShopVersion("v0.0.1");
                    socketChannel.writeAndFlush(versionReq);
            	}
			}catch (Exception e) {
				e.printStackTrace();
			}
        }
	}
	
	/**
	 * 重新连接
	 * @throws InterruptedException
	 */
	private void reconnect() throws InterruptedException {
		try {
			ChannelFuture future = bootstrap.connect(host, port).sync();
			if (future.isSuccess()) {
				socketChannel = (SocketChannel) future.channel();
				System.out.println("reconnect server is successful.");
				
		        LoginReq loginMsg=new LoginReq();
		        socketChannel.writeAndFlush(loginMsg);
			}
		} catch(Exception e) {
			System.out.println("重新连接异常，3秒后重试");
			TimeUnit.SECONDS.sleep(3);
		}
	}
}