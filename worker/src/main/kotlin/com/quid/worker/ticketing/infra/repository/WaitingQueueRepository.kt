package com.quid.worker.ticketing.infra.repository

import com.quid.worker.ticketing.domain.TicketMapper.jsonToEntity
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

    fun findFirstTicket(): WaitingQueue? {
        return queueRedisTemplate.opsForZSet().popMin(KEY)
            ?.let { jsonToEntity(it.value!!, it.score!!) }
    }

    fun isEmpty(): Boolean {
        return queueRedisTemplate.opsForZSet().size(KEY) == 0L
    }

    fun save(ticket: WaitingQueue) {
        queueRedisTemplate.opsForZSet().add(KEY, ticket.value(), ticket.score())
    }

    companion object {
        private const val KEY = "waiting_queue"
        private const val ACTIVE = "active::waiting_queue"
    }
}
