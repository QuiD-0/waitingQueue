package com.quid.entry.ticket.application

import com.quid.entry.ticket.domain.Ticket
import com.quid.entry.ticket.domain.TicketService
import org.springframework.stereotype.Service

@Service
class TicketingFacade(
    private val ticketService: TicketService,
) {
    fun proceed(domain: Ticket): String {
        //개발 테스트를 위해 무조건 대기열 등록
        if (true or ticketService.needWaiting(domain)) {
            ticketService.merge(domain)
            return "/waiting"
        }
        return domain.redirectUrl
    }

    fun getCurrentRank(redirectUrl: String, memberSeq: Long): Int {
        return ticketService.getCurrentRank(redirectUrl, memberSeq)
    }
}
