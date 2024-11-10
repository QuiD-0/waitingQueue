package com.quid.worker.ticketing.infra.scheduler

import com.quid.worker.ticketing.application.TicketingFacade
import com.quid.worker.ticketing.domain.WaitingQueueService
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class WorkerScheduler(
    private val ticketing: TicketingFacade,
    private val waitingQueueService: WaitingQueueService
) {
    val log = LoggerFactory.getLogger(this::class.java)!!

    @PostConstruct
    fun process() {
        for (i in 1..10) {
            Thread {
                while (true) {
                    worker()
                }
            }.start()
        }
    }

    private fun worker() {
        if (waitingQueueService.isEmpty()) {
            log.info("No ticket to process")
            return
        }
        log.info("Processing next ticket")
        ticketing.processNext()
        log.info("Ticket processed")
    }
}
