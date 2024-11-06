package com.quid.entry.execute.infra.repository

import com.quid.entry.execute.domain.Ticket
import com.quid.entry.execute.domain.TicketMapper
import com.quid.entry.execute.domain.TicketMapper.toWaiting
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

interface WaitingQueueRepository {
    fun getWaitingCount(targetUrl: String): Int
    fun getActiveCount(targetUrl: String): Int
    fun add(ticket: Ticket): Ticket
    fun existsBy(ticket: Ticket): Boolean
    fun findBy(ticket: Ticket): Ticket?
    fun getCurrentRank(redirectUrl: String, memberSeq: Long): Int
}

@Repository
class WaitingQueueRedisRepository(
    private val intTemplate: RedisTemplate<String, Int>,
    private val waitingQueueTemplate: RedisTemplate<String, Any>,
) : WaitingQueueRepository {
    override fun getWaitingCount(targetUrl: String): Int {
        return waitingQueueTemplate.opsForZSet().size(targetUrl)?.toInt() ?: 0
    }

    override fun getActiveCount(targetUrl: String): Int {
        return intTemplate.opsForValue().get("active::$targetUrl") ?: 0
    }

    override fun add(ticket: Ticket): Ticket {
        val waitingQueue = toWaiting(ticket)
        waitingQueueTemplate.opsForZSet().add(waitingQueue.key, waitingQueue.value, waitingQueue.score)
        return ticket
    }

    override fun existsBy(ticket: Ticket): Boolean {
        val waitingQueue = toWaiting(ticket)
        return waitingQueueTemplate.opsForZSet().score(waitingQueue.key, waitingQueue.value) != null
    }

    override fun findBy(ticket: Ticket): Ticket? {
        val waitingQueue = toWaiting(ticket)
        return waitingQueueTemplate.opsForZSet().score(waitingQueue.key, waitingQueue.value)
            ?.let { WaitingQueueEntity(waitingQueue.key, waitingQueue.value, it) }
            ?.let { TicketMapper.toDomain(it) }
    }

    override fun getCurrentRank(redirectUrl: String, memberSeq: Long): Int {
        return waitingQueueTemplate.opsForZSet().rank(redirectUrl, memberSeq)?.toInt() ?: -1
    }
}
