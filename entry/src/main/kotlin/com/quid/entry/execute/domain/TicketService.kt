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

    fun needWaiting(ticket: Ticket): Boolean {
        if (waitingQueueRepository.existsBy(ticket.redirectUrl, ticket.memberSeq)) {
            return true
        }
        if (waitingQueueRepository.getWaitingCount(ticket.redirectUrl) > 0) {
            return true
        }
        if (waitingQueueRepository.getActiveCount(ticket.redirectUrl) > limit) {
            return true
        }
        return false
    }

    fun getCurrentRank(redirectUrl: String, memberSeq: Long): Int {
        return waitingQueueRepository.getCurrentRank(redirectUrl, memberSeq)
    }
}
