package com.quid.entry.ticket.domain

import com.quid.entry.ticket.infra.repository.TicketRepository
import com.quid.entry.ticket.infra.repository.WaitingQueueRepository
import org.springframework.stereotype.Service

@Service
class TicketService(
    private val waitingQueueRepository: WaitingQueueRepository,
    private val ticketRepository: TicketRepository
) {
    fun merge(ticket: Ticket): Ticket {
        return ticketRepository.findBy(ticket.redirectUrl, ticket.memberSeq)
            ?: ticketRepository.save(ticket)
                .also { waitingQueueRepository.add(it) }
    }

    fun getCurrentRank(redirectUrl: String, memberSeq: Long): Int {
        return waitingQueueRepository.getCurrentRank(memberSeq)
    }

    fun isBeforeStarting(ticket: Ticket): Boolean {
        return ticket.isBefore(ticketRepository.findStartingTime(ticket.redirectUrl))
    }
}
