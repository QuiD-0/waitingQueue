package com.quid.entry.ticket.application

import com.quid.entry.ticket.domain.Ticket
import com.quid.entry.ticket.domain.TicketService
import org.springframework.stereotype.Service

@Service
class TicketingFacade(
    private val ticketService: TicketService,
) {
    fun proceed(domain: Ticket): String {
        if (ticketService.isBeforeStarting(domain)) {
            throw IllegalStateException("Ticketing is not started yet.")
        }
        ticketService.merge(domain)
        return "/waiting"
    }

    fun getCurrentRank(redirectUrl: String, memberSeq: Long): Int {
        return ticketService.getCurrentRank(redirectUrl, memberSeq)
    }
}
