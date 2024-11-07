package com.quid.entry.ticket.infra.repository

import java.time.LocalDateTime
import java.time.ZoneOffset.UTC

data class WaitingQueueEntity(
    val memberSeq: Long,
    val timestamp: LocalDateTime,
) {
    val score: Double
        get() = timestamp.toEpochSecond(UTC).toDouble()
    val value: Long
        get() = memberSeq
}
