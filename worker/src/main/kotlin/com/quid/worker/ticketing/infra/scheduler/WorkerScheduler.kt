package com.quid.worker.ticketing.infra.scheduler

import com.quid.worker.ticketing.application.TicketingUseCase
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextClosedEvent
import org.springframework.stereotype.Component
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Component
class WorkerScheduler(
    private val ticketing: TicketingUseCase,
) : ApplicationListener<ContextClosedEvent> {
    private val executorService: ExecutorService = Executors.newFixedThreadPool(10)
    private val log = LoggerFactory.getLogger(this::class.java)!!

    @PostConstruct
    fun init() {
        log.info("Starting worker")
        for (i in 0..10) {
            executorService.submit {
                while (true) {
                    ticketing.processNext()
                }
            }
        }
    }

    override fun onApplicationEvent(event: ContextClosedEvent) {
        executorService.shutdown()
        log.info("Shutting down worker")
    }
}
