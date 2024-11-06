package com.quid.entry.execute.infra.repository

import com.quid.entry.execute.domain.Ticket
import org.springframework.stereotype.Repository

@Repository
class TicketRepository {
    fun existsBy(redirectUrl: String, memberSeq: Long): Boolean {
        return false
    }

    fun findBy(ticket: String, memberSeq: Long): Ticket? {
        TODO("Not yet implemented")
    }

    fun save(ticket: Ticket): Ticket {
        TODO("Not yet implemented")
    }
}
