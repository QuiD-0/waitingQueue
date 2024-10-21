package com.quid.entry.execute.infra.repository

import org.springframework.stereotype.Repository

interface WaitingQueueRepository {
    fun getWaitingCount(): Int
    fun getActiveCount(): Int

}

@Repository
class WaitingQueueDemoRepository : WaitingQueueRepository {
    override fun getWaitingCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getActiveCount(): Int {
        TODO("Not yet implemented")
    }

}
