package com.quid.worker.ticketing.domain

import com.quid.worker.ticketing.infra.repository.WaitingQueueRepository
import org.springframework.stereotype.Service

@Service
class WaitingQueueService(
    private val waitingQueueRepository: WaitingQueueRepository
) {
    fun activeCountUp() {
        waitingQueueRepository.activeCountUp()
    }

    fun activeCountDown() {
        waitingQueueRepository.activeCountDown()
    }

    fun getFirstTicketMember(): Long {
        return waitingQueueRepository.findFirstTicket() ?: throw RuntimeException("Ticket not found")
    }
}
