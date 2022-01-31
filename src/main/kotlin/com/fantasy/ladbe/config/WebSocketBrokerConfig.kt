package com.fantasy.ladbe.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketBrokerConfig
    : WebSocketMessageBrokerConfigurer {
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/gs-guide-websocket") // 웹 소켓 연결을 위한 주소입니다.
            .setAllowedOrigins("*")
            .withSockJS()
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/queue", "/topic")
        /*
        바로 브로커에게 데이터를 전송할 때, 사용하는 prefix 입니다.
        queue : 1:1 메시지를 주고받을 때, 사용하는 Prefix
        topic : 1:N 메시지를 주고받을 때, 사용하는 Prefix
         */
        registry.setApplicationDestinationPrefixes("/app")
        // 메시지의 가공이 필요할 때, 사용하는 prefix 입니다.
    }


}
