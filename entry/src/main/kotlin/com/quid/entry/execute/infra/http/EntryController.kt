package com.quid.entry.execute.infra.http

import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class EntryController {
    val log = LoggerFactory.getLogger(this::class.java)!!

    @PostMapping("/entry")
    fun entry(response: HttpServletResponse, @RequestBody entryRequest: EntryRequest) {
        log.info("entry request: $entryRequest")
        response.sendRedirect(entryRequest.redirectUrl)
    }
}

data class EntryRequest(
    val memberSeq: Long,
    val redirectUrl: String
)
