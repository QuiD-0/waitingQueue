package com.quid.entry.execute.infra.repository

import com.quid.entry.execute.domain.Ticket
import com.quid.entry.execute.domain.TicketMapper.toWaiting
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

interface WaitingQueueRepository {
    fun getWaitingCount(targetUrl: String): Int
    fun getActiveCount(targetUrl: String): Int
    fun add(ticket: Ticket)
    fun getCurrentRank(key: String, value: Long): Int
    fun existsBy(key: String, value: Long): Boolean
}

@Repository
class WaitingQueueRedisRepository(
    private val intTemplate: RedisTemplate<String, Int>,
    private val waitingQueueTemplate: RedisTemplate<String, Long>,
) : WaitingQueueRepository {
    override fun getWaitingCount(targetUrl: String): Int {
        return waitingQueueTemplate.opsForZSet().size(targetUrl)?.toInt() ?: 0
    }

    override fun getActiveCount(targetUrl: String): Int {
        return intTemplate.opsForValue().get("active::$targetUrl") ?: 0
    }

    override fun add(ticket: Ticket) {
        val waitingQueue = toWaiting(ticket)
        waitingQueueTemplate.opsForZSet().add(waitingQueue.key, waitingQueue.value, waitingQueue.score)
    }

    override fun getCurrentRank(key: String, value: Long): Int {
        return waitingQueueTemplate.opsForZSet().rank(key, value)?.toInt() ?: throw RuntimeException("Ticket not found")
    }

    override fun existsBy(key: String, value: Long): Boolean {
        return waitingQueueTemplate.opsForZSet().score(key, value) != null
    }
}
