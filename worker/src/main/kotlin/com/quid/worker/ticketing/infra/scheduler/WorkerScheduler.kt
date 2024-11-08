package com.quid.worker.ticketing.infra.scheduler

import com.quid.worker.ticketing.application.TicketingFacade
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class WorkerScheduler(
    private val ticketing: TicketingFacade
) {
    val log = LoggerFactory.getLogger(this::class.java)!!

    @Scheduled(fixedDelay = 500)
    fun process() {
        ticketing.processNext()
        log.info("Processing next ticket")
    }
}
