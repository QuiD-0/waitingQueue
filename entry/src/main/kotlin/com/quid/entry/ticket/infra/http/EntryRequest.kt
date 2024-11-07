package com.quid.entry.ticket.infra.http

import com.quid.entry.ticket.domain.Ticket
import java.time.LocalDateTime

data class EntryRequest(
    val memberSeq: Long,
    val redirectUrl: String
) {
    fun toTicket() = Ticket(
        redirectUrl = redirectUrl,
        memberSeq = memberSeq,
        timestamp = LocalDateTime.now()
    )
}
