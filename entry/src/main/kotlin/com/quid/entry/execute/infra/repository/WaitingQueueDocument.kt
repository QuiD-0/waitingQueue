package com.quid.entry.execute.infra.repository

import org.springframework.data.redis.core.RedisHash
import java.time.LocalDateTime
import java.time.ZoneOffset

@RedisHash("waiting_queue")
data class WaitingQueueDocument(
    val redirectUrl: String,
    val memberSeq: Long,
    val timestamp: LocalDateTime,
) {
    val score: Double
        get() = timestamp.toEpochSecond(ZoneOffset.UTC).toDouble()
}
