package com.quid.entry.execute.domain

import com.quid.entry.execute.domain.WaitingQueueMapper.toDocument
import com.quid.entry.execute.domain.WaitingQueueMapper.toDomain
import com.quid.entry.execute.infra.repository.WaitingQueueRepository
import org.springframework.stereotype.Service

@Service
class WaitingQueueService(
    private val waitingQueueRepository: WaitingQueueRepository
) {
    fun append(waitingQueue: WaitingQueue) {
        return waitingQueueRepository.merge(toDocument(waitingQueue))
            .run { toDomain(this) }
    }

    fun checkDirectExecute(redirectUrl: String): Boolean {
        if (waitingQueueRepository.getWaitingCount(redirectUrl) > 0) {
            return false
        }
        if (waitingQueueRepository.getActiveCount(redirectUrl) > 0) {
            return false
        }
        return true
    }
}
