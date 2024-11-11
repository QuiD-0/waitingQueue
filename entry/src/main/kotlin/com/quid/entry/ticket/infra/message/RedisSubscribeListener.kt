package com.quid.entry.ticket.infra.message

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Component

@Component
class RedisSubscribeListener(
    private val objectMapper: ObjectMapper
) : MessageListener {
    override fun onMessage(message: Message, pattern: ByteArray?) {
        val readValue = objectMapper.readValue(message.body, NotifyUserMessage::class.java)
        println("message = $readValue")
    }
}
