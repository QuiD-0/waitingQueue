package com.quid.entry.execute.infra.repository

import org.springframework.stereotype.Repository

interface WaitingQueueRepository {
    fun getWaitingCount(targetUrl: String): Int
    fun getActiveCount(targetUrl: String): Int
    fun merge(waitingQueue: WaitingQueueDocument): WaitingQueueDocument
}

@Repository
class WaitingQueueDemoRepository : WaitingQueueRepository {
    override fun getWaitingCount(targetUrl: String): Int {
        TODO("Not yet implemented")
    }

    override fun getActiveCount(targetUrl: String): Int {
        TODO("Not yet implemented")
    }

    override fun merge(waitingQueue: WaitingQueueDocument): WaitingQueueDocument {
        TODO("Not yet implemented")
    }

}
