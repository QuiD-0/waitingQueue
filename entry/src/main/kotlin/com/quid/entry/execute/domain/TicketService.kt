package com.quid.entry.execute.domain

import com.quid.entry.execute.infra.repository.TicketRepository
import com.quid.entry.execute.infra.repository.WaitingQueueRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class TicketService(
    @Value("\${throttle.limit}")
    private val limit: Int,
    private val waitingQueueRepository: WaitingQueueRepository,
    private val ticketRepository: TicketRepository
) {
    fun merge(ticket: Ticket): Ticket {
        return ticketRepository.findBy(ticket.redirectUrl, ticket.memberSeq)
            ?: ticketRepository.save(ticket)
                .also { waitingQueueRepository.add(it) }
    }

    fun checkDirectExecute(ticket: Ticket): Boolean {
        if (ticketRepository.existsBy(ticket.redirectUrl, ticket.memberSeq)) {
            return false
        }
        if (waitingQueueRepository.getWaitingCount(ticket.redirectUrl) > 0) {
            return false
        }
        if (waitingQueueRepository.getActiveCount(ticket.redirectUrl) > limit) {
            return false
        }
        return true
    }

    fun getCurrentRank(redirectUrl: String, memberSeq: Long): Int {
        val ticket = ticketRepository.findBy(redirectUrl, memberSeq)
        return waitingQueueRepository.getCurrentRank(ticket)
    }
}
