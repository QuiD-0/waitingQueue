package com.quid.worker.ticketing.domain

import java.time.LocalDateTime
import java.time.ZoneOffset.UTC

data class WaitingQueue(
    val memberSeq: Long,
    val timestamp: LocalDateTime,
) {
    val score: Double
        get() = timestamp.toEpochSecond(UTC).toDouble()
    val value: Long
        get() = memberSeq
}
