package com.quid.entry.ticket.infra.repository

import com.quid.entry.ticket.domain.Ticket
import com.quid.entry.ticket.domain.TicketMapper.toWaiting
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

interface WaitingQueueRepository {
    fun getWaitingCount(): Int
    fun getActiveCount(): Int
    fun add(ticket: Ticket)
    fun getCurrentRank(value: Long): Int
    fun existsBy(value: Long): Boolean
}

@Repository
class WaitingQueueRedisRepository(
    private val intTemplate: RedisTemplate<String, Int>,
    private val waitingQueueTemplate: RedisTemplate<String, Long>,
) : WaitingQueueRepository {
    override fun getWaitingCount(): Int {
        return waitingQueueTemplate.opsForZSet().size(KEY)?.toInt() ?: 0
    }

    override fun getActiveCount(): Int {
        return intTemplate.opsForValue().get(ACTIVE) ?: 0
    }

    override fun add(ticket: Ticket) {
        val waitingQueue = toWaiting(ticket)
        waitingQueueTemplate.opsForZSet().add(KEY, waitingQueue.value, waitingQueue.score)
    }

    override fun getCurrentRank(value: Long): Int {
        return waitingQueueTemplate.opsForZSet().rank(KEY, value)?.toInt() ?: throw RuntimeException("Ticket not found")
    }

    override fun existsBy(value: Long): Boolean {
        return waitingQueueTemplate.opsForZSet().score(KEY, value) != null
    }

    companion object {
        private const val KEY = "waiting_queue"
        private const val ACTIVE = "active::waiting_queue"
    }
}
