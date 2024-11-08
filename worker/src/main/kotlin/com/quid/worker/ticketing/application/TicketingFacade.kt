package com.quid.worker.ticketing.application

import com.quid.worker.ticketing.domain.Ticket
import com.quid.worker.ticketing.domain.TicketService
import com.quid.worker.ticketing.domain.TicketStatus.PROCEED
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
        val processingTicket = ticketService.updateStatus(ticket, PROCEED)
        // 임의의 sleep으로 처리 시간을 나타냄
        // 티켓 처리 -> 레디스 퍼블리싱
        // 티켓 상태 변경
        // 액티브 카운트 감소
        waitingQueueService.activeCountDown()
    }
}
