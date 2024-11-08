package com.quid.worker.ticketing.domain

import java.time.LocalDateTime

data class Ticket(
    val id: String?,
    val redirectUrl: String,
    val memberSeq: Long,
    val timestamp: LocalDateTime,
    val status: TicketStatus,
) {
    fun updateStatus(status: TicketStatus): Ticket {
        return this.copy(status = status)
    }
}
