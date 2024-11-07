package com.quid.entry.execute.infra.repository

import com.quid.entry.execute.domain.TicketStatus
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("ticket")
data class TicketEntity(
    @Id
    val id: ObjectId,
    val redirectUrl: String,
    val memberSeq: Long,
    val timestamp: LocalDateTimeNano,
    val status: TicketStatus,
) {
}
