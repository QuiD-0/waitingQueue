package com.quid.entry.execute.domain

import com.quid.entry.execute.infra.repository.TicketEntity
import com.quid.entry.execute.infra.repository.WaitingQueueEntity

object TicketMapper {
    fun toDomain(waitingQueueEntity: WaitingQueueEntity): Ticket {
        return Ticket(
            redirectUrl = waitingQueueEntity.redirectUrl,
            memberSeq = waitingQueueEntity.memberSeq,
            timestamp = waitingQueueEntity.timestamp,
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
            timestamp = ticket.timestamp,
            status = ticket.status,
        )
    }
}
