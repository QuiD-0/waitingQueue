package com.quid.entry.ticket.infra.message

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Component
class SseEmitterService {
    private val emitters = ConcurrentHashMap<Long, SseEmitter>()
    private val log = LoggerFactory.getLogger(this::class.java)!!

    fun connect(memberSeq: Long): SseEmitter {
        log.info("connect memberSeq: $memberSeq")
        val emitter = SseEmitter().apply {
            onCompletion { emitters.remove(memberSeq) }
            onTimeout { emitters.remove(memberSeq) }
        }
        emitters[memberSeq] = emitter
        return emitter
    }

    fun send(memberSeq: Long, message: String) {
        val data = SseEmitter.event()
            .id(UUID.randomUUID().toString())
            .name("notify")
            .data(message)
        log.info("send message to memberSeq: $memberSeq")
        emitters[memberSeq]?.send(data)
        emitters.remove(memberSeq)
    }
}
