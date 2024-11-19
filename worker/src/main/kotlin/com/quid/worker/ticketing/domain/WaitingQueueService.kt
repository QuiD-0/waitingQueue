package com.quid.worker.ticketing.domain

import com.quid.worker.ticketing.domain.TicketMapper.toDomain
import com.quid.worker.ticketing.domain.TicketMapper.toEntity
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

    fun getFirstTicketMember(): Ticket? {
        return waitingQueueRepository.findFirstTicket()
            ?.let { toDomain(it) }
    }

    fun publish(ticket: Ticket) {
        notifyPublisher.publish(NotifyUserMessage(ticket.memberSeq))
    }

    fun save(ticket: Ticket) {
        waitingQueueRepository.save(toEntity(ticket))
    }
}
