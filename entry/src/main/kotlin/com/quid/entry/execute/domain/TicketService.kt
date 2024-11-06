package com.quid.entry.execute.domain

import com.quid.entry.execute.infra.repository.WaitingQueueRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class TicketService(
    @Value("\${throttle.limit}")
    private val limit: Int,
    private val waitingQueueRepository: WaitingQueueRepository
) {
    fun merge(ticket: Ticket): Ticket {
        return waitingQueueRepository.findBy(ticket)
            ?: waitingQueueRepository.add(ticket)
    }

    fun checkDirectExecute(redirectUrl: String): Boolean {
        if (waitingQueueRepository.getWaitingCount(redirectUrl) > 0) {
            return false
        }
        if (waitingQueueRepository.getActiveCount(redirectUrl) > limit) {
            return false
        }
        return true
    }

    fun getCurrentRank(redirectUrl: String, memberSeq: Long): Int {
        return waitingQueueRepository.getCurrentRank(redirectUrl, memberSeq)
    }
}
