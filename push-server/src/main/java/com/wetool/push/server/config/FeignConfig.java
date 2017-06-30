package com.wetool.push.server.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.support.ResponseEntityDecoder;
import org.springframework.cloud.netflix.feign.support.SpringDecoder;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.wetool.push.server.ContextHolder;
import feign.Feign;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.Decoder;
import feign.codec.Encoder;

@Configuration
public class FeignConfig {
	
	@Bean
	Logger feignLogger() {
		return new Logger.JavaLogger();
	}
	
	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
	
	public ObjectFactory<HttpMessageConverters> messageConverters() {
		return new ObjectFactory<HttpMessageConverters>() {
			@Override
			public HttpMessageConverters getObject() throws BeansException {
				return new HttpMessageConverters();
			}
		};
	}

	@Primary
	@Bean
	public RequestInterceptor requestTokenBearerInterceptor() {
	    return new RequestInterceptor() {
	        @Override
	        public void apply(RequestTemplate requestTemplate) {
	        	requestTemplate.header("Authorization", "Bearer " + ContextHolder.get());
	        }
	    };
	}
	
	@Bean
	public Encoder springEncoder() {
		return new SpringEncoder(messageConverters());
	}
	
	@Bean
	public Decoder feignDecoder() {
		return new ResponseEntityDecoder(new SpringDecoder(messageConverters()));
	}
	
	@Bean
	public Feign.Builder feignBuilder() {
		return Feign.builder()
				.encoder(springEncoder())
				.decoder(feignDecoder())
				.logger(feignLogger())
				.logLevel(feignLoggerLevel())
				.requestInterceptor(requestTokenBearerInterceptor());
	}
}