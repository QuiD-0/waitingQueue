package com.quid.worker.ticketing.domain

import com.quid.worker.ticketing.domain.TicketStatus.PROCEED
import com.quid.worker.ticketing.infra.repository.TicketRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.security.SecureRandom

@Service
class TicketService(
    private val ticketRepository: TicketRepository,
    private val waitingQueueService: WaitingQueueService,
) {
    val log = LoggerFactory.getLogger(this::class.java)!!

    fun findFirstTicket(): Ticket {
        val memberSeq: Long = waitingQueueService.getFirstTicketMember()
        return ticketRepository.findByMemberSeq(memberSeq)
            ?: throw RuntimeException("Ticket not found")
    }

    fun updateStatus(ticket: Ticket, status: TicketStatus): Ticket {
        val updateTicket = ticket.updateStatus(status)
        return ticketRepository.save(updateTicket)
    }

    fun enter(ticket: Ticket) {
        val proceed = updateStatus(ticket, PROCEED)
            .let { ticketRepository.save(it) }

        val seconds = SecureRandom().nextLong(10)
        println("Processing ticket: $ticket, $seconds seconds")
        Thread.sleep(seconds * 1000)
        log.info("Ticket processed: $ticket")

        proceed.updateStatus(TicketStatus.DONE)
            .let { ticketRepository.save(it) }
    }
}
