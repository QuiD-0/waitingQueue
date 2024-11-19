package com.quid.entry.ticket.infra.repository

import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class TicketRepository {
    fun findStartingTime(redirectUrl: String): LocalDateTime {
        return LocalDateTime.of(2024, 11, 1, 0, 0)
    }
}
