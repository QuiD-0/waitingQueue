package com.quid.worker.config

import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskExecutor
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling


@EnableAsync
@EnableScheduling
@Configuration
class SchedulerConfig {

    @Bean
    fun taskExecutor(): TaskExecutor {
        val executor = ThreadPoolTaskExecutorBuilder()
            .corePoolSize(10)
            .maxPoolSize(20)
            .queueCapacity(100)
            .threadNamePrefix("ticket-")
            .build()
        executor.initialize()
        return executor
    }
}
