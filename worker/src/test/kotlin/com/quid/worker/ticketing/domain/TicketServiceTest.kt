package com.quid.worker.ticketing.domain

import com.quid.worker.ticketing.infra.repository.TicketRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import java.time.LocalDateTime
import kotlin.test.assertEquals

class TicketServiceTest {
    private val ticketRepository: TicketRepository = mock()
    private val waitingQueueService: WaitingQueueService = mock()
    private val ticketService = TicketService(ticketRepository, waitingQueueService)

    @Test
    fun findFirstTicket() {
        given(waitingQueueService.getFirstTicketMember()).willReturn(1L)
        given(ticketRepository.findByMemberSeq(anyLong())).willReturn(ticket)

        assertEquals(ticket, ticketService.findFirstTicket())
    }

    @Test
    fun findFirstTicket_notFound() {
        given(waitingQueueService.getFirstTicketMember()).willReturn(1L)
        given(ticketRepository.findByMemberSeq(anyLong())).willReturn(null)

        assertThrows<RuntimeException> {
            ticketService.findFirstTicket()
        }
    }

    @Test
    fun updateStatus() {
        given(ticketRepository.save(proceed)).willReturn(proceed)

        assertEquals(proceed, ticketService.updateStatus(ticket, TicketStatus.PROCEED))
    }

    companion object {
        val ticket = Ticket(
            id = "id",
            redirectUrl = "url",
            memberSeq = 1L,
            timestamp = LocalDateTime.now(),
            status = TicketStatus.WAITING
        )

        val proceed = ticket.copy(status = TicketStatus.PROCEED)
    }

}
