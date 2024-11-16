package com.quid.entry.ticket.infra.http

import com.quid.entry.ticket.application.TicketingFacade
import com.quid.entry.ticket.application.WaitingFacade
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@RestController
class EntryController(
    private val ticketing: TicketingFacade,
    private val waiting: WaitingFacade
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
    ): Int {
        return waiting.getCurrentRank(redirectUrl, memberSeq)
    }

    @GetMapping("/sse", produces = ["text/event-stream"])
    fun connectSse(
        @RequestParam memberSeq: Long
    ): SseEmitter {
        return waiting.connectSse(memberSeq)
    }
}
