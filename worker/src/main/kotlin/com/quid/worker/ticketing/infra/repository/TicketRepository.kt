package com.quid.worker.ticketing.infra.repository

import com.quid.worker.ticketing.domain.Ticket
import com.quid.worker.ticketing.domain.TicketMapper
import com.quid.worker.ticketing.domain.TicketStatus
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
class TicketRepository(
    private val ticketMongoRepository: TicketMongoRepository
) {
    fun findByMemberSeq(memberSeq: Long): Ticket? {
        return ticketMongoRepository.findByMemberSeqAndStatusNot(memberSeq, TicketStatus.DONE.name)
            ?.let { TicketMapper.toDomain(it) }
    }

    fun save(ticket: Ticket): Ticket {
        val ticketEntity = TicketMapper.toEntity(ticket)
        return ticketMongoRepository.save(ticketEntity)
            .run { TicketMapper.toDomain(this) }
    }
}

interface TicketMongoRepository : MongoRepository<TicketEntity, ObjectId> {
    fun findByMemberSeqAndStatusNot(memberSeq: Long, status: String): TicketEntity?
}
