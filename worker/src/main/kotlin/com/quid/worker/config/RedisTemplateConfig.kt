package com.quid.worker.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
class RedisTemplateConfig(
    private val redisConnectionFactory: RedisConnectionFactory
) {

    @Bean
    fun <T> redisTemplate(): RedisTemplate<String, T> {
        return RedisTemplate<String, T>().apply {
            connectionFactory = redisConnectionFactory
            keySerializer = StringRedisSerializer()
            valueSerializer = GenericJackson2JsonRedisSerializer()
            afterPropertiesSet()
        }
    }
}
