package com.quid.entry.ticket.domain

import com.quid.entry.ticket.infra.repository.TicketRepository
import com.quid.entry.ticket.infra.repository.WaitingQueueRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import java.time.LocalDateTime
import kotlin.test.assertEquals

class TicketServiceTest {

    private val waitingQueueRepository = mock<WaitingQueueRepository>()
    private val ticketRepository = mock<TicketRepository>()
    private val ticketService = TicketService(waitingQueueRepository, ticketRepository)

    @Test
    @DisplayName("티케팅 전에 등록시 true")
    fun isBeforeStarting() {
        val ticket = Ticket("http://localhost:8080", 1, LocalDateTime.now())

        given(ticketRepository.findStartingTime(ticket.redirectUrl)).willReturn(LocalDateTime.now().plusHours(1))

        assertEquals(true, ticketService.isBeforeStarting(ticket))
    }

    @Test
    @DisplayName("티케팅 후에 등록시 false")
    fun isBeforeStarting2() {
        val ticket = Ticket("http://localhost:8080", 1, LocalDateTime.now())

        given(ticketRepository.findStartingTime(ticket.redirectUrl)).willReturn(LocalDateTime.now().plusHours(-1))

        assertEquals(false, ticketService.isBeforeStarting(ticket))
    }

}
