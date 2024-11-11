package com.quid.worker.ticketing.infra.message

import com.fasterxml.jackson.annotation.JsonProperty

data class NotifyUserMessage(
    @JsonProperty("memberSeq")
    private val memberSeq: Long,
)
