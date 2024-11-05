package com.quid.entry.execute.domain

import com.quid.entry.execute.infra.repository.WaitingQueueEntity

object WaitingQueueMapper {
    fun toDomain(waitingQueueEntity: WaitingQueueEntity): WaitingQueue {
        return WaitingQueue(
            redirectUrl = waitingQueueEntity.redirectUrl,
            memberSeq = waitingQueueEntity.memberSeq,
            timestamp = waitingQueueEntity.timestamp,
            waitingStatus = WaitingStatus.valueOf(waitingQueueEntity.status),
        )
    }

    fun toEntity(waitingQueue: WaitingQueue): WaitingQueueEntity {
        return WaitingQueueEntity(
            redirectUrl = waitingQueue.redirectUrl,
            memberSeq = waitingQueue.memberSeq,
            timestamp = waitingQueue.timestamp,
            status = waitingQueue.waitingStatus.name,
        )
    }
}
