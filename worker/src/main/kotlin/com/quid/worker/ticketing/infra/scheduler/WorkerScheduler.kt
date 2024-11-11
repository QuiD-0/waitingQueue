package com.quid.worker.ticketing.infra.scheduler

import com.quid.worker.ticketing.application.TicketingFacade
import com.quid.worker.ticketing.domain.WaitingQueueService
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextClosedEvent
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Order(1)
@Component
class WorkerScheduler(
    private val ticketing: TicketingFacade,
    private val waitingQueueService: WaitingQueueService,
) : ApplicationListener<ContextClosedEvent> {
    private val executorService: ExecutorService = Executors.newFixedThreadPool(10)
    private val log = LoggerFactory.getLogger(this::class.java)!!

    @PostConstruct
    fun init() {
        log.info("Starting worker")
        for (i in 0..9) {
            executorService.submit {
                while (true) {
                    worker()
                }
            }
        }
    }

    override fun onApplicationEvent(event: ContextClosedEvent) {
        executorService.shutdown()
        log.info("Shutting down worker")
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
