package com.quid.entry.execute.usecase

import org.springframework.stereotype.Service

@Service
class EntryService {
    fun decideUrl(memberSeq: Long, redirectUrl: String): String {
        return "https://www.google.com"
    }
}
