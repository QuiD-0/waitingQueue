package com.quid.worker.ticketing.application

import com.quid.worker.ticketing.domain.Ticket
import com.quid.worker.ticketing.domain.TicketService
import com.quid.worker.ticketing.domain.WaitingQueueService
import org.springframework.stereotype.Component

@Component
class TicketingFacade(
    private val ticketService: TicketService,
    private val waitingQueueService: WaitingQueueService
) {
    fun processNext() {
        waitingQueueService.activeCountUp()
        val ticket: Ticket = ticketService.findFirstTicket()
        ticketService.enter(ticket)
        waitingQueueService.publish(ticket)
        waitingQueueService.activeCountDown()
    }
}
