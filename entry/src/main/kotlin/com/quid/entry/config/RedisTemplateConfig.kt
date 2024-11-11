package com.quid.entry.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
class RedisTemplateConfig {

    @Bean
    fun <T> redisTemplate(connectionFactory: RedisConnectionFactory?): RedisTemplate<String, T> {
        return RedisTemplate<String, T>().apply {
            setConnectionFactory(connectionFactory!!)
            keySerializer = StringRedisSerializer()
            valueSerializer = GenericJackson2JsonRedisSerializer()
            afterPropertiesSet()
        }
    }
}
