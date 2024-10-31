package com.quid.entry.execute.application

import com.quid.entry.execute.domain.WaitingQueue
import org.springframework.stereotype.Service

@Service
class WaitingQueueWorker {
    fun proceed(toWaitingQueue: WaitingQueue): String {
        return ""
    }
}
