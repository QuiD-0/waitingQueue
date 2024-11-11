package com.quid.worker.ticketing.infra.message

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class NotifyPublisher(
    private val redisTemplate: RedisTemplate<String, String>,
) {
    private val log = LoggerFactory.getLogger(this::class.java)!!
    private val objectMapper: ObjectMapper = jacksonObjectMapper()

    fun publish(message: NotifyUserMessage) {
        log.info("Publishing message: $message")
        redisTemplate.convertAndSend("notify", objectMapper.writeValueAsString(message))
    }
}
