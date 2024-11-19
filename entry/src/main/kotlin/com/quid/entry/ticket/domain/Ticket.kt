package com.quid.entry.ticket.domain

import java.time.LocalDateTime

data class Ticket(
    val redirectUrl: String,
    val memberSeq: Long,
    val timestamp: LocalDateTime,
) {
    fun isBefore(findStartingTime: LocalDateTime): Boolean {
        return timestamp.isBefore(findStartingTime)
    }
}
