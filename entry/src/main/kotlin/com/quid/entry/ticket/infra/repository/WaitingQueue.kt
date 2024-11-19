package com.quid.entry.ticket.infra.repository

import java.time.LocalDateTime
import java.time.ZoneOffset.UTC

data class WaitingQueue(
    val memberSeq: Long,
    val redirectUrl: String,
    val timestamp: LocalDateTime,
) {
    val score: Double
        get() = (timestamp.toEpochSecond(UTC) * 1_000_000_000 + timestamp.nano).toDouble()

    fun value(): String {
        return """
            {
                "memberSeq": $memberSeq,
                "redirectUrl": "$redirectUrl"
            }
        """.trimIndent()
    }
}


