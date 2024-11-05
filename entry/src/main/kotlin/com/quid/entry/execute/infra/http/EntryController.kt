package com.quid.entry.execute.infra.http

import com.quid.entry.execute.application.TicketingFacade
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class EntryController(
    private val worker: TicketingFacade
) {
    val log = LoggerFactory.getLogger(this::class.java)!!

    @PostMapping("/entry")
    fun entry(@RequestBody entry: EntryRequest, response: HttpServletResponse) {
        log.info("entry request: $entry")
        worker.proceed(entry.toTicket())
            .let { response.sendRedirect(it) }
    }
}
