package com.quid.entry.execute.infra.repository

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class LocalDateTimeNanoTest {

    @Test
    fun toLocalDateTimeNano() {
        val time = LocalDateTime.now()
        val localDateTimeNano = LocalDateTimeNano(time)

        assertEquals(time, localDateTimeNano.toLocalDateTime())
        assertEquals(time.nano, localDateTimeNano.toLocalDateTime().nano)
    }

    @Test
    fun toLocalDateTime() {
        val time = LocalDateTime.now()
        val localDateTimeNano = LocalDateTimeNano(time)

        assertEquals(time, localDateTimeNano.toLocalDateTime())
    }

}
