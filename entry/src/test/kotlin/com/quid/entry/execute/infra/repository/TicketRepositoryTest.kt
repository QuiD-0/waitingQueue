package com.quid.entry.execute.infra.repository

import com.quid.entry.execute.domain.Ticket
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.TestConstructor.AutowireMode.ALL
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest
@ActiveProfiles("dev")
@TestConstructor(autowireMode = ALL)
class TicketRepositoryTest(
    private val ticketRepository: TicketRepository,
    private val mongoTemplate: MongoTemplate
) {
    @BeforeEach
    fun setUp() {
        mongoTemplate.dropCollection("ticket")
    }

    @Test
    fun existsBy() {
        val ticket = ticket(LocalDateTime.now())
        ticketRepository.save(ticket)
        
        val result = ticketRepository.existsBy(ticket.redirectUrl, ticket.memberSeq)

        assertTrue { result }
    }

    @Test
    fun findBy() {
        val ticket = ticket(LocalDateTime.now())
        ticketRepository.save(ticket)

        val result = ticketRepository.findBy(ticket.redirectUrl, ticket.memberSeq)

        assertEquals(ticket, result)
    }

    @Test
    fun save() {
        val ticket = ticket(LocalDateTime.now())

        assertDoesNotThrow { ticketRepository.save(ticket) }
    }

    fun ticket(time: LocalDateTime): Ticket {
        return Ticket(
            redirectUrl = "http://localhost:8080",
            memberSeq = 1L,
            timestamp = time,
        )
    }
}
