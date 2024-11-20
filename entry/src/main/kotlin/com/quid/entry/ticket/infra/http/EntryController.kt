package com.quid.entry.ticket.infra.http

import com.quid.entry.ticket.application.TicketIncomeUseCase
import com.quid.entry.ticket.domain.TicketService
import com.quid.entry.ticket.infra.message.SseEmitterService
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@RestController
class EntryController(
    private val ticketing: TicketIncomeUseCase,
    private val ticketService: TicketService,
    private val emitterService: SseEmitterService
) {
    val log = LoggerFactory.getLogger(this::class.java)!!

    @PostMapping("/entry")
    fun entry(@RequestBody entry: EntryRequest): EntryResponse {
        log.info("entry request: $entry")
        ticketing.proceed(entry.toTicket())
        return EntryResponse("/waiting")
    }

    @GetMapping("/queue")
    fun getCurrentRank(
        @RequestParam redirectUrl: String,
        @RequestParam memberSeq: Long
    ): QueueResponse {
        val currentRank = ticketService.getCurrentRank(redirectUrl, memberSeq)
        return QueueResponse(currentRank)
    }

    @GetMapping("/sse", produces = [TEXT_EVENT_STREAM_VALUE])
    fun connectSse(
        @RequestParam memberSeq: Long
    ): SseEmitter {
        return emitterService.connect(memberSeq)
    }
}
