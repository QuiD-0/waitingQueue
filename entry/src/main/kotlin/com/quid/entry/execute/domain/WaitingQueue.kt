package com.quid.entry.execute.domain

import java.time.LocalDateTime

data class WaitingQueue(
    val redirectUrl: String,
    val memberSeq: Long,
    val timestamp: LocalDateTime,
) {
}
