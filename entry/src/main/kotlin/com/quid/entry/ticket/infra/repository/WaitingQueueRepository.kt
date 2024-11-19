package com.quid.entry.ticket.infra.repository

import com.quid.entry.ticket.domain.Ticket
import com.quid.entry.ticket.domain.TicketMapper.toWaiting
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

interface WaitingQueueRepository {
    fun getWaitingCount(): Int
    fun getActiveCount(): Int
    fun add(ticket: Ticket)
    fun getCurrentRank(ticket: Ticket): Int
}

@Repository
class WaitingQueueRedisRepository(
    private val waitingQueueTemplate: RedisTemplate<String, String>,
) : WaitingQueueRepository {
    override fun getWaitingCount(): Int {
        return waitingQueueTemplate.opsForZSet().size(KEY)?.toInt() ?: 0
    }

    override fun getActiveCount(): Int {
        return waitingQueueTemplate.opsForValue().get(ACTIVE)?.toInt() ?: 0
    }

    override fun add(ticket: Ticket) {
        val waitingQueue = toWaiting(ticket)
        waitingQueueTemplate.opsForZSet().addIfAbsent(KEY, waitingQueue.value(), waitingQueue.score)
    }

    override fun getCurrentRank(ticket: Ticket): Int {
        val waitingQueue = toWaiting(ticket)
        return waitingQueueTemplate.opsForZSet().rank(KEY, waitingQueue.value())?.toInt()
            ?: throw RuntimeException("Ticket not found")
    }

    companion object {
        private const val KEY = "waiting_queue"
        private const val ACTIVE = "active::waiting_queue"
    }
}
