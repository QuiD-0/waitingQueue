package com.quid.worker.ticketing.domain

import java.time.LocalDateTime

data class Ticket(
    val redirectUrl: String,
    val memberSeq: Long,
    val timestamp: LocalDateTime,
) {
}
