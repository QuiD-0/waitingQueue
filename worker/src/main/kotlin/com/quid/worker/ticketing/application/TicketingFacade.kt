package com.quid.worker.ticketing.application

import com.quid.worker.ticketing.domain.TicketService
import com.quid.worker.ticketing.infra.repository.RedisClient
import org.springframework.stereotype.Component

@Component
class TicketingFacade(
    private val ticketService: TicketService,
    private val redisClient: RedisClient
) {
    fun processNext() {
        // 액티브 카운트 증가
        // 가장 빨리 들어온 티켓 가져오기
        // 티켓 상태 변경
        // 임의의 sleep으로 처리 시간을 나타냄
        // 티켓 처리 -> 레디스 퍼블리싱
        // 티켓 상태 변경
        // 액티브 카운트 감소
    }
}
