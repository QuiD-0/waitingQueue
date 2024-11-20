package com.quid.worker.ticketing.infra.repository

import com.quid.worker.ticketing.domain.Ticket
import com.quid.worker.ticketing.domain.TicketMapper.jsonToEntity
import com.quid.worker.ticketing.domain.TicketMapper.toDomain
import com.quid.worker.ticketing.domain.TicketMapper.toEntity
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

    fun findFirstTicket(): Ticket? {
        return queueRedisTemplate.opsForZSet().popMin(KEY)
            ?.let { jsonToEntity(it.value!!, it.score!!) }
            ?.let { toDomain(it) }
    }

    fun isEmpty(): Boolean {
        return queueRedisTemplate.opsForZSet().size(KEY) == 0L
    }

    fun save(ticket: Ticket) {
        val waitingQueue = toEntity(ticket)
        queueRedisTemplate.opsForZSet().add(KEY, waitingQueue.value(), waitingQueue.score())
    }

    companion object {
        private const val KEY = "waiting_queue"
        private const val ACTIVE = "active::waiting_queue"
    }
}
