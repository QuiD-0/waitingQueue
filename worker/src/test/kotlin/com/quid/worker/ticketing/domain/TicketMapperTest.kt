package com.quid.worker.ticketing.domain

import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneOffset.UTC
import kotlin.test.assertEquals

class TicketMapperTest {

    @Test
    fun jsonToEntity() {
        val json = """
      {
          "memberSeq": 6,
          "redirectUrl": "https://www.naver.com"
      }
     """.trimIndent()

        val waitingQueue = TicketMapper.jsonToEntity(json, 0.0)
        assertEquals(6, waitingQueue.memberSeq)
    }

    @Test
    fun scoreToLocalDateTime() {
        val timestamp: LocalDateTime = LocalDateTime.now()
        val score = (timestamp.toEpochSecond(UTC) * 1_000_000_000 + timestamp.nano).toDouble()

        val second = score.toLong() / 1_000_000_000
        val nano = (score.toLong() % 1_000_000_000).toInt()

        val ofEpochSecond = LocalDateTime.ofEpochSecond(second, nano, UTC)
    }
}
