package com.quid.entry.ticket.application

import com.quid.entry.ticket.domain.TicketService
import com.quid.entry.ticket.infra.message.SseEmitterService
import org.springframework.stereotype.Component
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@Component
class WaitingFacade(
    private val ticketService: TicketService,
    private val emitterService: SseEmitterService
) {

    fun getCurrentRank(redirectUrl: String, memberSeq: Long): Int {
        return ticketService.getCurrentRank(redirectUrl, memberSeq)
    }

    fun connectSse(memberSeq: Long): SseEmitter {
        return emitterService.connect(memberSeq)
    }
}
