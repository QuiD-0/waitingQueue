package com.quid.entry.execute.domain

import com.quid.entry.execute.infra.repository.WaitingQueueDocument

object WaitingQueueMapper {
    fun toDomain(waitingQueueDocument: WaitingQueueDocument): WaitingQueue {
        return WaitingQueue(
            redirectUrl = waitingQueueDocument.redirectUrl,
            memberSeq = waitingQueueDocument.memberSeq,
            timestamp = waitingQueueDocument.timestamp,
            id = waitingQueueDocument.id
        )
    }

    fun toDocument(waitingQueue: WaitingQueue): WaitingQueueDocument {
        return WaitingQueueDocument(
            redirectUrl = waitingQueue.redirectUrl,
            memberSeq = waitingQueue.memberSeq,
            timestamp = waitingQueue.timestamp,
            id = waitingQueue.id
        )
    }
}
