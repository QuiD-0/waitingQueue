package com.quid.entry.execute.application

import com.quid.entry.execute.domain.Ticket
import com.quid.entry.execute.domain.TicketService
import org.springframework.stereotype.Service

@Service
class TicketingFacade(
    private val ticketService: TicketService,
) {
    fun proceed(domain: Ticket): String {
        if (ticketService.checkDirectExecute(domain.redirectUrl)) {
            return domain.redirectUrl
        }
        ticketService.merge(domain)
        return "/waiting"
    }

    fun getCurrentRank(redirectUrl: String, memberSeq: Long): Int {
        return ticketService.getCurrentRank(redirectUrl, memberSeq)
    }
}
