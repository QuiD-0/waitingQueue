package com.quid.worker.ticketing.domain

import com.quid.worker.ticketing.infra.repository.TicketRepository
import com.quid.worker.ticketing.infra.repository.WaitingQueueRepository
import org.springframework.stereotype.Service

@Service
class TicketService(
    private val ticketRepository: TicketRepository,
    private val waitingQueueRepository: WaitingQueueRepository,
) {
}
