package com.quid.worker.ticket.domain

data class Ticket(
    val id: Long,
    val redirectUrl: String,
    val memberSeq: Long,
    val timestamp: String,
    val status: String,
) {
}
