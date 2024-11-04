package com.quid.entry.execute.domain

import com.quid.entry.execute.infra.repository.WaitingQueueReadEntity
import com.quid.entry.execute.infra.repository.WaitingQueueSaveEntity

object WaitingQueueMapper {
    fun toDomain(waitingQueueReadEntity: WaitingQueueReadEntity): WaitingQueue {
        return WaitingQueue(
            redirectUrl = waitingQueueReadEntity.redirectUrl,
            memberSeq = waitingQueueReadEntity.memberSeq,
            timestamp = waitingQueueReadEntity.timestamp,
            status = Status.valueOf(waitingQueueReadEntity.status),
        )
    }

    fun toReadEntity(waitingQueue: WaitingQueue): WaitingQueueReadEntity {
        return WaitingQueueReadEntity(
            redirectUrl = waitingQueue.redirectUrl,
            memberSeq = waitingQueue.memberSeq,
            timestamp = waitingQueue.timestamp,
            status = waitingQueue.status.name,
        )
    }

    fun toSaveEntity(waitingQueue: WaitingQueue): WaitingQueueSaveEntity {
        return WaitingQueueSaveEntity(
            redirectUrl = waitingQueue.redirectUrl,
            memberSeq = waitingQueue.memberSeq,
            status = waitingQueue.status.name,
            score = 0.0,
        )
    }
}
