package com.quid.entry.execute.usecase

import com.quid.entry.execute.domain.WaitingQueue
import com.quid.entry.execute.infra.repository.WaitingQueueRepository
import org.springframework.stereotype.Service

@Service
class WaitingQueueService(
    private val waitingQueueRepository: WaitingQueueRepository
) {
    fun append(waitingQueue: WaitingQueue) {
        waitingQueueRepository.merge(waitingQueue)
    }
}
