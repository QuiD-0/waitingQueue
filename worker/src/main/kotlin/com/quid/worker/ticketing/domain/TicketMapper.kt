package com.quid.worker.ticketing.domain

import com.quid.worker.ticketing.infra.repository.LocalDateTimeNano
import com.quid.worker.ticketing.infra.repository.TicketEntity
import org.bson.types.ObjectId

object TicketMapper {
    fun toEntity(ticket: Ticket): TicketEntity {
        return TicketEntity(
            id = ticket.id?.let { ObjectId(it) } ?: ObjectId(),
            redirectUrl = ticket.redirectUrl,
            memberSeq = ticket.memberSeq,
            timestamp = LocalDateTimeNano(ticket.timestamp),
            status = ticket.status.name
        )
    }

    fun toDomain(ticketEntity: TicketEntity): Ticket {
        return Ticket(
            id = ticketEntity.id.toHexString(),
            redirectUrl = ticketEntity.redirectUrl,
            memberSeq = ticketEntity.memberSeq,
            timestamp = ticketEntity.timestamp.toLocalDateTime(),
            status = TicketStatus.valueOf(ticketEntity.status)
        )
    }

}
