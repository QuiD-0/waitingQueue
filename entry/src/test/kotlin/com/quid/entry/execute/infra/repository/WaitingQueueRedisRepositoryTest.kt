package com.quid.entry.execute.infra.repository

import com.quid.entry.execute.domain.WaitingQueue
import com.quid.entry.execute.domain.WaitingQueueMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.TestConstructor.AutowireMode.ALL
import java.time.LocalDateTime

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
        val domain = WaitingQueue(
            redirectUrl = TARGET_URL,
            memberSeq = 1L,
            timestamp = LocalDateTime.now()
        )

        redis.add(WaitingQueueMapper.toSaveEntity(domain))
        val result = redis.getWaitingCount(TARGET_URL)

        assertEquals(1, result)
    }

    companion object {
        private const val TARGET_URL = "http://localhost:8080"
    }
}
