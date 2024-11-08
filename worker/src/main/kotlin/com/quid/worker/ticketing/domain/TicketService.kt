package com.quid.worker.ticketing.domain

import com.quid.worker.ticketing.infra.repository.TicketRepository
import org.springframework.stereotype.Service

@Service
class TicketService(
    private val ticketRepository: TicketRepository,
    private val waitingQueueService: WaitingQueueService,
) {

    fun findFirstTicket(): Ticket {
        val memberSeq: Long = waitingQueueService.getFirstTicketMember()
        return ticketRepository.findByMemberSeq(memberSeq)
            ?: throw IllegalArgumentException("Ticket not found")
    }

    fun updateStatus(ticket: Ticket, status: TicketStatus): Ticket {
        val updateTicket = ticket.updateStatus(status)
        return ticketRepository.save(updateTicket)
    }
}
