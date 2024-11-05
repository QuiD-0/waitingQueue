package com.quid.entry.execute.infra.http

import com.quid.entry.execute.domain.Ticket
import java.time.LocalDateTime

data class EntryRequest(
    val memberSeq: Long,
    val redirectUrl: String
) {
    fun toTicket() = Ticket(redirectUrl, memberSeq, LocalDateTime.now())
}
