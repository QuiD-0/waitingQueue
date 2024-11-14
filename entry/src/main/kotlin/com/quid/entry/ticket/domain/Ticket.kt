package com.quid.entry.ticket.domain

import org.bson.types.ObjectId
import java.time.LocalDateTime

data class Ticket(
    val id: ObjectId,
    val redirectUrl: String,
    val memberSeq: Long,
    val timestamp: LocalDateTime,
    val status: TicketStatus,
) {
    fun isBefore(findStartingTime: LocalDateTime): Boolean {
        return timestamp.isBefore(findStartingTime)
    }

    constructor(
        redirectUrl: String,
        memberSeq: Long,
        timestamp: LocalDateTime,
    ) : this(
        id = ObjectId.get(),
        redirectUrl = redirectUrl,
        memberSeq = memberSeq,
        timestamp = timestamp,
        status = TicketStatus.WAITING,
    )
}
