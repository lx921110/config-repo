package com.wetool;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wetool.push.api.model.Constants;
import com.wetool.push.api.model.client.CategoryReq;
import com.wetool.push.api.model.client.CommodityReq;
import com.wetool.push.api.model.client.LoginReq;
import com.wetool.push.api.model.client.SimpleTest;
import com.wetool.push.api.model.client.VersionReq;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.socket.SocketChannel;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	private final String host = "192.168.1.70"; // 服务地址
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
		Constants.setClientId("17070200010001");
		Constants.setToken("1f0b422e-f5f3-4123-8ace-7454b108bd59");
		
        LoginReq loginMsg=new LoginReq();
        socketChannel.writeAndFlush(loginMsg);
        
        
        while (true){
        	try {
        		if(!socketChannel.isActive()) {	// 如果通道断开
            		reconnect();	// 重新连接
            	} else {
            		TimeUnit.SECONDS.sleep(30);
            		CommodityReq commodityMsg = new CommodityReq();
            		commodityMsg.setMerchantId(1L);
            		commodityMsg.setSize(30);
            		commodityMsg.setUpdateDate("");
            		commodityMsg.setPage(10L);
            		CategoryReq categoryReq = new CategoryReq();
            		categoryReq.setMerchantId(1L);
            		socketChannel.writeAndFlush(categoryReq);
                    VersionReq versionReq=new VersionReq();
                    versionReq.setAdvertVersion("v1.0.0");
        			versionReq.setCashierVersion("v1.0.0");
        			versionReq.setFirmwareVersion("v1.0.0");
        			versionReq.setPosVersion("v1.0.0");
        			versionReq.setShopVersion("v1.0.0");
                    socketChannel.writeAndFlush(commodityMsg);
                    SimpleTest test = new SimpleTest();
                    test.setHello("FUCK........................................U............");
                    socketChannel.writeAndFlush(test);
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