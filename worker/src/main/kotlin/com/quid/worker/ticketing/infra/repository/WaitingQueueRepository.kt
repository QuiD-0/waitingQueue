package com.quid.worker.ticketing.infra.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class WaitingQueueRepository(
    private val queueRedisTemplate: RedisTemplate<String, String>,
) {

    fun activeCountUp() {
        queueRedisTemplate.opsForValue().increment(ACTIVE)
    }

    fun activeCountDown() {
        queueRedisTemplate.opsForValue().decrement(ACTIVE)
    }

    fun findFirstTicket(): Long? {
        return queueRedisTemplate.opsForZSet().popMin(KEY)?.value?.toLong()
    }

    fun isEmpty(): Boolean {
        return queueRedisTemplate.opsForZSet().size(KEY) == 0L
    }

    companion object {
        private const val KEY = "waiting_queue"
        private const val ACTIVE = "active::waiting_queue"
    }
}
