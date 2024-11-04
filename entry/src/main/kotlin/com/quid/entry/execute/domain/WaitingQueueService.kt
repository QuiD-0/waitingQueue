package com.quid.entry.execute.domain

import com.quid.entry.execute.domain.WaitingQueueMapper.toEntity
import com.quid.entry.execute.infra.repository.WaitingQueueRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class WaitingQueueService(
    @Value("\${throttle.limit}")
    private val limit: Int,
    private val waitingQueueRepository: WaitingQueueRepository
) {
    fun merge(waitingQueue: WaitingQueue) {
        val entity = toEntity(waitingQueue)
        if (!waitingQueueRepository.existsBy(entity)) {
            waitingQueueRepository.add(entity)
        }
    }

    fun checkDirectExecute(redirectUrl: String): Boolean {
        if (waitingQueueRepository.getWaitingCount(redirectUrl) > 0) {
            return false
        }
        if (waitingQueueRepository.getActiveCount(redirectUrl) > limit) {
            return false
        }
        return true
    }
}
