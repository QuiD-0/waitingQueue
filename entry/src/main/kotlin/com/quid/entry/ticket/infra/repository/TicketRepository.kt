package com.quid.entry.ticket.infra.repository

import com.quid.entry.ticket.domain.Ticket
import com.quid.entry.ticket.domain.TicketMapper
import com.quid.entry.ticket.domain.TicketStatus
import com.quid.entry.ticket.domain.TicketStatus.WAITING
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class TicketRepository(
    private val ticketMongoRepository: TicketMongoRepository
) {
    fun existsBy(redirectUrl: String, memberSeq: Long): Boolean {
        return ticketMongoRepository.existsByRedirectUrlAndMemberSeq(redirectUrl, memberSeq)
    }

    fun findBy(ticket: String, memberSeq: Long): Ticket? {
        val ticketEntity = ticketMongoRepository.findByRedirectUrlAndMemberSeqAndStatus(ticket, memberSeq, WAITING)
        return ticketEntity?.let { TicketMapper.toDomain(it) }
    }

    fun save(ticket: Ticket): Ticket {
        val ticketEntity = TicketMapper.toTicketEntity(ticket)
        return TicketMapper.toDomain(ticketMongoRepository.save(ticketEntity))
    }

    fun findStartingTime(redirectUrl: String): LocalDateTime {
        return LocalDateTime.of(2024, 11, 1, 0, 0)
    }
}

interface TicketMongoRepository : MongoRepository<TicketEntity, ObjectId> {
    fun existsByRedirectUrlAndMemberSeq(redirectUrl: String, memberSeq: Long): Boolean
    fun findByRedirectUrlAndMemberSeqAndStatus(
        redirectUrl: String,
        memberSeq: Long,
        status: TicketStatus
    ): TicketEntity?
}
