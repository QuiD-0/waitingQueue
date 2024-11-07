package com.quid.entry.execute.domain

import com.quid.entry.execute.infra.repository.LocalDateTimeNano
import com.quid.entry.execute.infra.repository.TicketEntity
import com.quid.entry.execute.infra.repository.WaitingQueueEntity

object TicketMapper {

    fun toDomain(ticketEntity: TicketEntity): Ticket {
        return Ticket(
            id = ticketEntity.id,
            redirectUrl = ticketEntity.redirectUrl,
            memberSeq = ticketEntity.memberSeq,
            timestamp = ticketEntity.timestamp.toLocalDateTime(),
            status = ticketEntity.status,
        )
    }

    fun toWaiting(ticket: Ticket): WaitingQueueEntity {
        return WaitingQueueEntity(
            redirectUrl = ticket.redirectUrl,
            memberSeq = ticket.memberSeq,
            timestamp = ticket.timestamp,
        )
    }

    fun toTicketEntity(ticket: Ticket): TicketEntity {
        return TicketEntity(
            id = ticket.id,
            redirectUrl = ticket.redirectUrl,
            memberSeq = ticket.memberSeq,
            timestamp = LocalDateTimeNano(ticket.timestamp),
            status = ticket.status,
        )
    }
}
