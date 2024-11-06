package com.quid.entry.execute.domain

import java.time.LocalDateTime
import java.time.ZoneOffset.UTC


data class Ticket(
    val id: Long = 0,
    val redirectUrl: String,
    val memberSeq: Long,
    val timestamp: LocalDateTime = LocalDateTime.now(UTC),
    val status: TicketStatus = TicketStatus.WAITING,
)

enum class TicketStatus {
    WAITING,
    PROCEED,
    DONE,
}

