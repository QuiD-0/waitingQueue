package com.quid.worker.ticketing.domain

import com.quid.worker.ticketing.infra.repository.LocalDateTimeNano
import com.quid.worker.ticketing.infra.repository.TicketEntity

object TicketMapper {
    fun toTicketEntity(ticket: Ticket): TicketEntity {
        return TicketEntity(
            id = ticket.id,
            redirectUrl = ticket.redirectUrl,
            memberSeq = ticket.memberSeq,
            timestamp = LocalDateTimeNano(ticket.timestamp),
            status = ticket.status
        )
    }

    fun toDomain(ticketEntity: TicketEntity): Ticket {
        return Ticket(
            id = ticketEntity.id,
            redirectUrl = ticketEntity.redirectUrl,
            memberSeq = ticketEntity.memberSeq,
            timestamp = ticketEntity.timestamp.toLocalDateTime(),
            status = ticketEntity.status
        )
    }
}
