package com.quid.worker.ticketing.infra.repository

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("ticket")
data class TicketEntity(
    @Id
    val id: ObjectId,
    val redirectUrl: String,
    val memberSeq: Long,
    val timestamp: LocalDateTimeNano,
    val status: String,
)

class LocalDateTimeNano(
    private val timestamp: LocalDateTime = LocalDateTime.now(),
    private val nano: Int = timestamp.nano
) {

    fun toLocalDateTime(): LocalDateTime {
        return timestamp.withNano(nano)
    }
}
