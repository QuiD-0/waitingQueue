package com.quid.entry.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
class RedisTemplateConfig(
    @Value("\${spring.data.redis.host}")
    private val host: String,
    @Value("\${spring.data.redis.port}")
    private val port: Int
) {

    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory {
        return LettuceConnectionFactory(RedisStandaloneConfiguration(host, port))
    }

    @Bean
    fun <T> redisTemplate(connectionFactory: RedisConnectionFactory?): RedisTemplate<String, T> {
        return RedisTemplate<String, T>().apply {
            setConnectionFactory(connectionFactory!!)
            keySerializer = StringRedisSerializer()
            valueSerializer = StringRedisSerializer()
            afterPropertiesSet()
        }
    }
}
