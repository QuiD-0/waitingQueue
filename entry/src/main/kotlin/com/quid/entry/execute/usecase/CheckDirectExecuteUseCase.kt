package com.quid.entry.execute.usecase

import com.quid.entry.execute.infra.repository.WaitingQueueRepository
import org.springframework.stereotype.Service

@Service
class CheckDirectExecuteUseCase(
    private val waitingQueueRepository: WaitingQueueRepository
) {
    fun invoke(memberSeq: Long): Boolean {
        if (waitingQueueRepository.getWaitingCount() > 0) {
            return false
        }
        if (waitingQueueRepository.getActiveCount() > 0) {
            return false
        }
        return true
    }
}
