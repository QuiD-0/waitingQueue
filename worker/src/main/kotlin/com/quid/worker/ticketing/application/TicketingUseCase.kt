package com.quid.worker.ticketing.application

import com.quid.worker.ticketing.domain.TicketService
import com.quid.worker.ticketing.domain.WaitingQueueService
import com.quid.worker.ticketing.infra.message.NotifyPublisher
import com.quid.worker.ticketing.infra.message.NotifyUserMessage
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class TicketingUseCase(
    private val ticketService: TicketService,
    private val waitingQueueService: WaitingQueueService,
    private val notifyPublisher: NotifyPublisher
) {
    private val log = LoggerFactory.getLogger(this::class.java)!!

    fun processNext() {
        log.info("Processing next ticket")
        val ticket = waitingQueueService.findFirstTicket() ?: return

        try {
            waitingQueueService.activeCountUp()
            ticketService.enter(ticket)
            notifyPublisher.publish(NotifyUserMessage(ticket.memberSeq))
        } catch (e: Exception) {
            log.error("Failed to process ticket", e)
            waitingQueueService.save(ticket)
        } finally {
            waitingQueueService.activeCountDown()
        }
    }
}
