package com.quid.entry.ticket.domain

import com.quid.entry.ticket.infra.repository.TicketRepository
import com.quid.entry.ticket.infra.repository.WaitingQueueRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TicketService(
    private val waitingQueueRepository: WaitingQueueRepository,
    private val ticketRepository: TicketRepository,
) {
    fun merge(ticket: Ticket) {
        waitingQueueRepository.add(ticket)
    }

    fun getCurrentRank(redirectUrl: String, memberSeq: Long): Int {
        val ticket = Ticket(redirectUrl, memberSeq, LocalDateTime.MAX)
        return waitingQueueRepository.getCurrentRank(ticket)
    }

    fun isBeforeStarting(ticket: Ticket): Boolean {
        return ticket.isBefore(ticketRepository.findStartingTime(ticket.redirectUrl))
    }
}
