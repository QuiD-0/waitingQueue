package com.quid.entry.ticket.domain

import com.quid.entry.ticket.infra.repository.WaitingQueue

object TicketMapper {
    fun toWaiting(ticket: Ticket): WaitingQueue {
        return WaitingQueue(
            memberSeq = ticket.memberSeq,
            redirectUrl = ticket.redirectUrl,
            timestamp = ticket.timestamp
        )
    }
}
