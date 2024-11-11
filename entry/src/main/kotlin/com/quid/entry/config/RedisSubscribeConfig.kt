package com.quid.entry.config

import com.quid.entry.ticket.infra.message.RedisSubscribeListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer


@Configuration
class RedisSubscribeConfig(
    private val redisConnectionFactory: RedisConnectionFactory,
    private val listener: RedisSubscribeListener
) {

    @Bean
    fun redisMessageListener(): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(redisConnectionFactory)
        container.addMessageListener(listener, ChannelTopic("notify"))
        return container
    }
}
