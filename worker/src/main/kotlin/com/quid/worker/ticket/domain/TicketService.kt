package com.quid.worker.ticket.domain

import com.quid.worker.ticket.infra.repository.TicketRepository
import org.springframework.stereotype.Service

@Service
class TicketService(
    private val ticketRepository: TicketRepository
) {
}
