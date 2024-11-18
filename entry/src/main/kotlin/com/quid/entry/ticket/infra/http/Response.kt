package com.quid.entry.ticket.infra.http

data class EntryResponse(
    val returnUrl: String
) {
}

data class QueueResponse(
    val rank: Int
) {
}
