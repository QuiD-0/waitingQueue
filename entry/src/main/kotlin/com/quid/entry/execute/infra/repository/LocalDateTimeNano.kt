package com.quid.entry.execute.infra.repository

import java.time.LocalDateTime

class LocalDateTimeNano(
    private val timestamp: LocalDateTime = LocalDateTime.now(),
    private val nano: Int = timestamp.nano
) {

    fun toLocalDateTime(): LocalDateTime {
        return timestamp.withNano(nano)
    }
}
