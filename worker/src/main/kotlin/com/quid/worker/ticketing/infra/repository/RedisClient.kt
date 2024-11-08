package com.quid.worker.ticketing.infra.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class RedisClient(
    private val redisTemplate: RedisTemplate<String, Any>
) {

}
