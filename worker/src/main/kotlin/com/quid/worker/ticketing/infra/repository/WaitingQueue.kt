package com.quid.worker.ticketing.infra.repository

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.time.ZoneOffset.UTC

data class WaitingQueue(
    @JsonProperty("memberSeq")
    val memberSeq: Long,
    @JsonProperty("redirectUrl")
    val redirectUrl: String,
    @JsonProperty("timestamp")
    val timestamp: String?,
) {
    fun score(): Double {
        val localDateTime = LocalDateTime.parse(timestamp!!)
        return (localDateTime.toEpochSecond(UTC) * 1_000_000_000 + localDateTime.nano).toDouble()
    }

    fun value(): String {
        return """
            {
                "memberSeq": $memberSeq,
                "redirectUrl": "$redirectUrl"
            }
        """.trimIndent()
    }
}
