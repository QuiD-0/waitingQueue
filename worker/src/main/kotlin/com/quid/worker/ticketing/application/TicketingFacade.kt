package com.quid.worker.ticketing.application

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
        log.info("Processing next ticket")
        val ticket = ticketService.findFirstTicket() ?: return

        try {
            waitingQueueService.activeCountUp()
            ticketService.enter(ticket)
            waitingQueueService.publish(ticket)
        } catch (e: Exception) {
            e.printStackTrace()
            waitingQueueService.save(ticket)
        } finally {
            waitingQueueService.activeCountDown()
        }
    }
}
