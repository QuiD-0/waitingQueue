package com.quid.entry.execute.infra.repository

import java.time.LocalDateTime
import java.time.ZoneOffset.UTC

data class WaitingQueueEntity(
    val redirectUrl: String,
    val memberSeq: Long,
    val timestamp: LocalDateTime,
) {
    val score: Double
        get() = timestamp.toEpochSecond(UTC).toDouble()
    val key: String
        get() = redirectUrl
    val value: Long
        get() = memberSeq
}
