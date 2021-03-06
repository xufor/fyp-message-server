package com.akash.app;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.RequestUpgradeStrategy;
import org.springframework.web.socket.server.standard.TomcatRequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@SpringBootConfiguration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/stomp-endpoint").setAllowedOriginPatterns("http://*").withSockJS();

		// extra configuration for java client
		RequestUpgradeStrategy upgradeStrategy = new TomcatRequestUpgradeStrategy();
		registry.addEndpoint("/stomp-endpoint").setHandshakeHandler(new DefaultHandshakeHandler(upgradeStrategy))
				.setAllowedOriginPatterns("http://*", "ws://*");
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic");
		registry.setApplicationDestinationPrefixes("/app");

	}
}
