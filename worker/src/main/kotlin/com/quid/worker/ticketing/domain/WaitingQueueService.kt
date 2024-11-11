package com.quid.worker.ticketing.domain

import com.quid.worker.ticketing.infra.message.NotifyPublisher
import com.quid.worker.ticketing.infra.message.NotifyUserMessage
import com.quid.worker.ticketing.infra.repository.WaitingQueueRepository
import org.springframework.stereotype.Service

@Service
class WaitingQueueService(
    private val waitingQueueRepository: WaitingQueueRepository,
    private val notifyPublisher: NotifyPublisher
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

    fun publish(ticket: Ticket) {
        notifyPublisher.publish(NotifyUserMessage(ticket.memberSeq))
    }

    fun isEmpty(): Boolean {
        return waitingQueueRepository.isEmpty()
    }
}
