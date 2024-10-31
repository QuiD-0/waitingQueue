package com.quid.entry.execute.infra.repository

import com.quid.entry.execute.domain.WaitingQueue
import org.springframework.stereotype.Repository

interface WaitingQueueRepository {
    fun getWaitingCount(targetUrl: String): Int
    fun getActiveCount(targetUrl: String): Int
    fun merge(waitingQueue: WaitingQueue)
}

@Repository
class WaitingQueueDemoRepository : WaitingQueueRepository {
    override fun getWaitingCount(targetUrl: String): Int {
        TODO("Not yet implemented")
    }

    override fun getActiveCount(targetUrl: String): Int {
        TODO("Not yet implemented")
    }

    override fun merge(waitingQueue: WaitingQueue) {
        TODO("Not yet implemented")
    }

}
