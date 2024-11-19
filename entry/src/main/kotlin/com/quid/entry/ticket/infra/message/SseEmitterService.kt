package com.quid.entry.ticket.infra.message

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.concurrent.ConcurrentHashMap

@Component
class SseEmitterService {
    private val emitters = ConcurrentHashMap<Long, SseEmitter>()
    private val log = LoggerFactory.getLogger(this::class.java)!!

    fun connect(memberSeq: Long): SseEmitter {
        log.info("connect memberSeq: $memberSeq")

        val emitter = SseEmitter(100_000L).apply {
            onCompletion { emitters.remove(memberSeq) }
            onTimeout { emitters.remove(memberSeq) }
        }

        val data = makeData("CONNECTED")

        emitters[memberSeq] = emitter
        emitter.send(data)
        return emitter
    }

    fun send(memberSeq: Long, message: String) {
        val data = makeData(message)
        log.info("send message to memberSeq: $memberSeq")
        val sseEmitter = emitters[memberSeq]
        sseEmitter?.send(data)
        sseEmitter?.complete()
        emitters.remove(memberSeq)
    }

    private fun makeData(message: String): SseEmitter.SseEventBuilder {
        return SseEmitter.event()
            .name("notify")
            .data(message)
    }
}
