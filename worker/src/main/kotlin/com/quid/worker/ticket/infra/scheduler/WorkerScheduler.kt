package com.quid.worker.ticket.infra.scheduler

import com.quid.worker.ticket.application.TicketingFacade
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@Configuration
@EnableScheduling
class WorkerScheduler(
    private val ticketing: TicketingFacade
) {

    @Scheduled(fixedDelay = 500)
    fun process() {
        ticketing.processNext()
    }
}
