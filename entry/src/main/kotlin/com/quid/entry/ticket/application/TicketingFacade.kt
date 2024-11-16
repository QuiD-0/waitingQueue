package com.quid.entry.ticket.application

import com.quid.entry.ticket.domain.Ticket
import com.quid.entry.ticket.domain.TicketService
import org.springframework.stereotype.Service

@Service
class TicketingFacade(
    private val ticketService: TicketService,
) {
    fun proceed(domain: Ticket) {
        if (ticketService.isBeforeStarting(domain)) {
            throw IllegalStateException("Ticketing is not started yet.")
        }
        ticketService.merge(domain)
    }
}
