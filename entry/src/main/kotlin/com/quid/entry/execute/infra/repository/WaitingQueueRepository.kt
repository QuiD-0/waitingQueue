package com.quid.entry.execute.infra.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

interface WaitingQueueRepository {
    fun getWaitingCount(targetUrl: String): Int
    fun getActiveCount(targetUrl: String): Int
    fun add(waitingQueue: WaitingQueueDocument)
    fun existsBy(waitingQueue: WaitingQueueDocument): Boolean
    fun findBy(waitingQueue: WaitingQueueDocument): WaitingQueueDocument?
}

@Repository
class WaitingQueueRedisRepository(
    private val redisRepository: RedisTemplate<String, Long>
) : WaitingQueueRepository {
    override fun getWaitingCount(targetUrl: String): Int {
        return redisRepository.opsForZSet().size(targetUrl)?.toInt() ?: 0
    }

    override fun getActiveCount(targetUrl: String): Int {
        return redisRepository.opsForValue().get("active::$targetUrl")?.toInt() ?: 0
    }

    override fun add(waitingQueue: WaitingQueueDocument) {
        redisRepository.opsForZSet().add(waitingQueue.redirectUrl, waitingQueue.memberSeq, waitingQueue.score)
    }

    override fun existsBy(waitingQueue: WaitingQueueDocument): Boolean {
        return redisRepository.opsForZSet().score(waitingQueue.redirectUrl, waitingQueue.memberSeq) != null
    }

    override fun findBy(waitingQueue: WaitingQueueDocument): WaitingQueueDocument? {
        return redisRepository.opsForZSet().score(waitingQueue.redirectUrl, waitingQueue.memberSeq)
            ?.let { WaitingQueueDocument(waitingQueue.redirectUrl, waitingQueue.memberSeq, waitingQueue.timestamp) }
    }
}
