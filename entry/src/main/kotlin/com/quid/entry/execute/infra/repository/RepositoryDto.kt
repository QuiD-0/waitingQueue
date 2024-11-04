package com.quid.entry.execute.infra.repository

import java.time.LocalDateTime
import java.time.ZoneOffset.UTC

data class WaitingQueueEntity(
    val redirectUrl: String,
    val memberSeq: Long,
    val status: String,
    val timestamp: LocalDateTime,
) {
    constructor(redirectUrl: String, value: WaitingQueueValue, timestamp: Double) : this(
        redirectUrl = redirectUrl,
        memberSeq = value.memberSeq,
        status = value.status,
        timestamp = LocalDateTime.ofEpochSecond(timestamp.toLong(), value.nano, UTC),
    )

    val score: Double
        get() = timestamp.toEpochSecond(UTC).toDouble()
    val key: String
        get() = redirectUrl
    val value: WaitingQueueValue
        get() = WaitingQueueValue(memberSeq, status, timestamp.nano)
}

data class WaitingQueueValue(
    val memberSeq: Long,
    val status: String,
    val nano: Int,
)
