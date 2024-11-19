package com.quid.worker.ticketing.domain

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.security.SecureRandom

@Service
class TicketService(
    private val waitingQueueService: WaitingQueueService,
) {
    val log = LoggerFactory.getLogger(this::class.java)!!

    fun findFirstTicket(): Ticket? {
        return waitingQueueService.getFirstTicketMember()
    }

    fun enter(ticket: Ticket) {
        val seconds = SecureRandom().nextLong(10)
        log.info("Processing ticket: $ticket, $seconds seconds")
        Thread.sleep(seconds * 1000)
        log.info("Ticket processed: $ticket")
    }
}
