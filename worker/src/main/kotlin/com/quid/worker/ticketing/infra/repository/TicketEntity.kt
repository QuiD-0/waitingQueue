package com.quid.worker.ticketing.infra.repository

import org.bson.types.ObjectId
import java.time.LocalDateTime

data class TicketEntity(
    val id: ObjectId,
    val redirectUrl: String,
    val memberSeq: Long,
    val timestamp: LocalDateTimeNano,
    val status: String,
) {
}

class LocalDateTimeNano(
    private val timestamp: LocalDateTime = LocalDateTime.now(),
    private val nano: Int = timestamp.nano
) {

    fun toLocalDateTime(): LocalDateTime {
        return timestamp.withNano(nano)
    }
}
