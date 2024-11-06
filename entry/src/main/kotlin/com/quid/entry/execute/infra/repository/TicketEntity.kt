package com.quid.entry.execute.infra.repository

import com.quid.entry.execute.domain.TicketStatus
import java.time.LocalDateTime

data class TicketEntity(
    val id: Long,
    val redirectUrl: String,
    val memberSeq: Long,
    val timestamp: LocalDateTime,
    val status: TicketStatus,
)
