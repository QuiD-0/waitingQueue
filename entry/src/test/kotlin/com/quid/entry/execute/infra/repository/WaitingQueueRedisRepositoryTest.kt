package com.quid.entry.execute.infra.repository

import com.quid.entry.execute.domain.Ticket
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.TestConstructor.AutowireMode.ALL
import java.time.LocalDateTime

@Disabled
@SpringBootTest
@ActiveProfiles("dev")
@TestConstructor(autowireMode = ALL)
class WaitingQueueRedisRepositoryTest(
    private val redis: WaitingQueueRedisRepository,
    private val redisTemplate: RedisTemplate<String, Any>
) {
    @AfterEach
    fun tearDown() {
        redisTemplate.delete(TARGET_URL)
    }

    @Test
    fun getWaitingCount() {
        val result = redis.getWaitingCount(TARGET_URL)

        assertEquals(0, result)
    }

    @Test
    fun add() {
        val domain = ticket(LocalDateTime.now())
        redis.add(domain)
        val result = redis.getWaitingCount(TARGET_URL)

        assertEquals(1, result)
    }

    @Test
    fun getCurrentRank() {
        val domain = ticket(LocalDateTime.now())

        redis.add(ticket(LocalDateTime.now().minusHours(1)))
        redis.add(domain)
        redis.add(ticket(LocalDateTime.now().plusHours(1)))
        redis.add(ticket(LocalDateTime.now().plusHours(2)))
        val result = redis.getCurrentRank(domain.redirectUrl, domain.memberSeq)

        assertEquals(1, result)
    }

    fun ticket(time: LocalDateTime): Ticket {
        return Ticket(
            redirectUrl = TARGET_URL,
            memberSeq = 1L,
            timestamp = time
        )
    }

    companion object {
        private const val TARGET_URL = "http://localhost:8080"
    }
}
