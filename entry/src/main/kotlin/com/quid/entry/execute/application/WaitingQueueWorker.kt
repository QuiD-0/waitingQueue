package com.quid.entry.execute.application

import com.quid.entry.execute.domain.WaitingQueue
import com.quid.entry.execute.domain.WaitingQueueService
import org.springframework.stereotype.Service

@Service
class WaitingQueueWorker(
    private val waitingQueueService: WaitingQueueService
) {
    fun proceed(domain: WaitingQueue): String {
        if (waitingQueueService.checkDirectExecute(domain.redirectUrl)) {
            return domain.redirectUrl
        }

        waitingQueueService.append(domain)
        return "/waiting"
    }
}
