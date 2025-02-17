package com.quid.entry.ticket.infra.scheduler

import com.quid.entry.ticket.infra.repository.WaitingQueueRepository
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@Configuration
@EnableScheduling
class SizeLogger(
    private val repository: WaitingQueueRepository
) {
    private val log = LoggerFactory.getLogger(this::class.java)!!

    @Scheduled(fixedRate = 1000)
    fun log() {
        val findQueueSize = repository.findQueueSize()
        log.info("Queue size: $findQueueSize")
    }
}
