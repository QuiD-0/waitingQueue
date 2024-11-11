package com.quid.worker.ticketing.infra.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class WaitingQueueRepository(
    private val queueRedisTemplate: RedisTemplate<String, String>,
    private val activeCountRedisTemplate: RedisTemplate<String, Int>
) {

    fun activeCountUp() {
        activeCountRedisTemplate.opsForValue().increment(ACTIVE)
    }

    fun activeCountDown() {
        activeCountRedisTemplate.opsForValue().decrement(ACTIVE)
    }

    fun findFirstTicket(): Long? {
        return queueRedisTemplate.opsForZSet().range(KEY, 0, 0)
            ?.run { this.first().toLong() }
            .also { queueRedisTemplate.opsForZSet().remove(KEY, it.toString()) }
    }

    fun isEmpty(): Boolean {
        return queueRedisTemplate.opsForZSet().size(KEY) == 0L
    }

    companion object {
        private const val KEY = "waiting_queue"
        private const val ACTIVE = "active::waiting_queue"
    }
}
