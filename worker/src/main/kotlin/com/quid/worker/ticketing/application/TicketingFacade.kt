package com.quid.worker.ticketing.application

import com.quid.worker.ticketing.domain.Ticket
import com.quid.worker.ticketing.domain.TicketService
import com.quid.worker.ticketing.domain.WaitingQueueService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class TicketingFacade(
    private val ticketService: TicketService,
    private val waitingQueueService: WaitingQueueService
) {
    private val log = LoggerFactory.getLogger(this::class.java)!!

    fun processNext() {
        try {
            waitingQueueService.activeCountUp()
            val ticket: Ticket = ticketService.findFirstTicket()
            log.info("found ticket: $ticket")
            ticketService.enter(ticket)
            waitingQueueService.publish(ticket)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            waitingQueueService.activeCountDown()
        }
    }
}
