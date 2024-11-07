package com.quid.entry.ticket.infra.http

import com.quid.entry.ticket.application.TicketingFacade
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class EntryController(
    private val worker: TicketingFacade,
) {
    val log = LoggerFactory.getLogger(this::class.java)!!

    @PostMapping("/entry")
    fun entry(@RequestBody entry: EntryRequest, response: HttpServletResponse) {
        log.info("entry request: $entry")
        worker.proceed(entry.toTicket())
            .let { response.sendRedirect(it) }
    }

    @GetMapping("/queue/{memberSeq}")
    fun getCurrentRank(
        @RequestParam redirectUrl: String,
        @PathVariable(value = "memberSeq") memberSeq: Long,
        httpServletResponse: HttpServletResponse
    ): Int {
        return worker.getCurrentRank(redirectUrl, memberSeq)
    }
}
