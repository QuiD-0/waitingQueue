package com.quid.worker.ticket.infra.scheduler

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@Configuration
@EnableScheduling
class WorkerScheduler {

    @Scheduled(fixedDelay = 500)
    fun process() {

    }
}
