package com.quid.entry.ticket.infra.message

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Component

@Component
class RedisSubscribeListener(
    private val sseEmitterService: SseEmitterService
) : MessageListener {
    private val objectMapper: ObjectMapper = jacksonObjectMapper()

    override fun onMessage(message: Message, pattern: ByteArray?) {
        val readValue = objectMapper.readValue(String(message.body), NotifyUserMessage::class.java)
        sseEmitterService.send(readValue.memberSeq, "COMPLETE")
    }
}
