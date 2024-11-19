package com.quid.worker.ticketing.domain

import com.fasterxml.jackson.databind.ObjectMapper
import com.quid.worker.ticketing.infra.repository.WaitingQueue
import java.time.LocalDateTime
import java.time.ZoneOffset.UTC

object TicketMapper {
    fun toEntity(ticket: Ticket): WaitingQueue {
        return WaitingQueue(
            redirectUrl = ticket.redirectUrl,
            memberSeq = ticket.memberSeq,
            timestamp = ticket.timestamp.toString(),
        )
    }

    fun toDomain(waitingQueue: WaitingQueue): Ticket {
        return Ticket(
            redirectUrl = waitingQueue.redirectUrl,
            memberSeq = waitingQueue.memberSeq,
            timestamp = LocalDateTime.parse(waitingQueue.timestamp!!),
        )
    }

    fun jsonToEntity(json: String, score: Double): WaitingQueue {
        val waitingQueue = ObjectMapper().readValue(json, WaitingQueue::class.java)
        val second = score.toLong() / 1_000_000_000
        val nano = (score.toLong() % 1_000_000_000).toInt()
        val ofEpochSecond = LocalDateTime.ofEpochSecond(second, nano, UTC)
        return waitingQueue.copy(
            timestamp = ofEpochSecond.toString(),
        )
    }
}
