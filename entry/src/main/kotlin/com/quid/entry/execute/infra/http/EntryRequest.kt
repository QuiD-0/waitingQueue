package com.quid.entry.execute.infra.http

import com.quid.entry.execute.domain.WaitingQueue
import java.time.LocalDateTime

data class EntryRequest(
    val memberSeq: Long,
    val redirectUrl: String
) {
    fun toWaitingQueue() = WaitingQueue(redirectUrl, memberSeq, LocalDateTime.now())
}
