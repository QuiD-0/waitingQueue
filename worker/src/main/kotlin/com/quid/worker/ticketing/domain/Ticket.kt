package com.quid.worker.ticketing.domain

import org.bson.types.ObjectId
import java.time.LocalDateTime

data class Ticket(
    val id: ObjectId,
    val redirectUrl: String,
    val memberSeq: Long,
    val timestamp: LocalDateTime,
    val status: String,
) {
    val waitingQueue: WaitingQueue
        get() = WaitingQueue(memberSeq, timestamp)
}
