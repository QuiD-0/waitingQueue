package com.quid.entry.execute.infra.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

interface WaitingQueueRepository {
    fun getWaitingCount(targetUrl: String): Int
    fun getActiveCount(targetUrl: String): Int
    fun add(waitingQueue: WaitingQueueEntity): WaitingQueueEntity
    fun existsBy(waitingQueue: WaitingQueueEntity): Boolean
    fun findBy(waitingQueue: WaitingQueueEntity): WaitingQueueEntity?
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

    override fun add(waitingQueue: WaitingQueueEntity): WaitingQueueEntity {
        waitingQueueTemplate.opsForZSet().add(waitingQueue.key, waitingQueue.value, waitingQueue.score)
        return waitingQueue
    }

    override fun existsBy(waitingQueue: WaitingQueueEntity): Boolean {
        return waitingQueueTemplate.opsForZSet().score(waitingQueue.key, waitingQueue.value) != null
    }

    override fun findBy(waitingQueue: WaitingQueueEntity): WaitingQueueEntity? {
        return waitingQueueTemplate.opsForZSet().score(waitingQueue.key, waitingQueue.value)
            ?.let { WaitingQueueEntity(waitingQueue.key, waitingQueue.value, it) }
    }
}
