package com.quid.entry.ticket.domain

import com.quid.entry.ticket.infra.repository.TicketRepository
import com.quid.entry.ticket.infra.repository.WaitingQueueRepository
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import java.time.LocalDateTime

class TicketServiceTest {

    private val waitingQueueRepository = mock<WaitingQueueRepository>()
    private val ticketRepository = mock<TicketRepository>()
    private val ticketService = TicketService(10, waitingQueueRepository, ticketRepository)

    @Test
    @DisplayName("이미 대기열에 등록된경우 대기 필요")
    fun needWaiting1() {
        val ticket = Ticket("http://localhost:8080", 1, LocalDateTime.now())

        given(waitingQueueRepository.existsBy(ticket.memberSeq)).willReturn(true)

        assertTrue(ticketService.needWaiting(ticket))
    }

    @Test
    @DisplayName("대기열이 활성화된 경우 대기 필요")
    fun needWaiting2() {
        val ticket = Ticket("http://localhost:8080", 1, LocalDateTime.now())

        given(waitingQueueRepository.existsBy(ticket.memberSeq)).willReturn(false)
        given(waitingQueueRepository.getWaitingCount()).willReturn(10)

        assertTrue(ticketService.needWaiting(ticket))
    }

    @Test
    @DisplayName("서버 입계값이 제한을 초과한 경우 대기 필요")
    fun needWaiting3() {
        val ticket = Ticket("http://localhost:8080", 1, LocalDateTime.now())

        given(waitingQueueRepository.existsBy(ticket.memberSeq)).willReturn(false)
        given(waitingQueueRepository.getWaitingCount()).willReturn(0)
        given(waitingQueueRepository.getActiveCount()).willReturn(11)

        assertTrue(ticketService.needWaiting(ticket))
    }
}
