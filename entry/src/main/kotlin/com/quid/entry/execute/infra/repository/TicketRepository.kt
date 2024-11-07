package com.quid.entry.execute.infra.repository

import com.quid.entry.execute.domain.Ticket
import com.quid.entry.execute.domain.TicketMapper
import org.springframework.stereotype.Repository

@Repository
class TicketRepository(
    private val ticketMongoRepository: TicketMongoRepository
) {
    fun existsBy(redirectUrl: String, memberSeq: Long): Boolean {
        return ticketMongoRepository.existsByRedirectUrlAndMemberSeq(redirectUrl, memberSeq)
    }

    fun findBy(ticket: String, memberSeq: Long): Ticket? {
        val ticketEntity = ticketMongoRepository.findByRedirectUrlAndMemberSeq(ticket, memberSeq)
        return ticketEntity?.let { TicketMapper.toDomain(it) }
    }

    fun save(ticket: Ticket): Ticket {
        val ticketEntity = TicketMapper.toTicketEntity(ticket)
        return TicketMapper.toDomain(ticketMongoRepository.save(ticketEntity))
    }
}
