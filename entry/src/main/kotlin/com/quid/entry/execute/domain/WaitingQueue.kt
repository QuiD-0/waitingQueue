package com.quid.entry.execute.domain

import java.time.LocalDateTime
import java.util.*

data class WaitingQueue(
    val redirectUrl: String,
    val memberSeq: Long,
    val timestamp: LocalDateTime,
    val id: String = UUID.randomUUID().toString(),
) {
}
