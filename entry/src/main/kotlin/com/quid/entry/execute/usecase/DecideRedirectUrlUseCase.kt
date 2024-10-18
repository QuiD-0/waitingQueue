package com.quid.entry.execute.usecase

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class DecideRedirectUrlUseCase(
    @Value("\${entry.waiting-page}")
    private val waitingPage: String,
    private val checkDirectExecute: CheckDirectExecuteUseCase
) {
    fun invoke(memberSeq: Long, redirectUrl: String): String {
        return takeIf { checkDirectExecute.invoke(memberSeq) }
            ?.let { redirectUrl }
            ?: waitingPage
    }
}
