package com.quid.worker.ticketing.infra.message

import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class NotifyPublisher(
    private val redisTemplate: RedisTemplate<String, NotifyUserMessage>
) {
    private val log = LoggerFactory.getLogger(this::class.java)!!

    fun publish(message: NotifyUserMessage) {
        log.info("Publishing message: $message")
        redisTemplate.convertAndSend("notify", message)
    }
}
