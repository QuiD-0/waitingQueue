package com.quid.entry.execute.domain

import com.quid.entry.execute.infra.repository.WaitingQueueEntity

object TicketMapper {
    fun toDomain(waitingQueueEntity: WaitingQueueEntity): Ticket {
        return Ticket(
            redirectUrl = waitingQueueEntity.redirectUrl,
            memberSeq = waitingQueueEntity.memberSeq,
            timestamp = waitingQueueEntity.timestamp,
        )
    }

    fun toEntity(ticket: Ticket): WaitingQueueEntity {
        return WaitingQueueEntity(
            redirectUrl = ticket.redirectUrl,
            memberSeq = ticket.memberSeq,
            timestamp = ticket.timestamp,
        )
    }
}
