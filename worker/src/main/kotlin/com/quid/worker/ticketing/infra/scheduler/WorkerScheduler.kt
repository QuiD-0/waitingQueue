package com.quid.worker.ticketing.infra.scheduler

import com.quid.worker.ticketing.application.TicketingFacade
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
