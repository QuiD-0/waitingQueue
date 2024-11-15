package com.quid.entry.ticket.infra.message

import org.springframework.stereotype.Component
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.concurrent.ConcurrentHashMap

@Component
class SseEmitterService {
    private val emitters = ConcurrentHashMap<Long, SseEmitter>()

    fun connect(memberSeq: Long): SseEmitter {
        val emitter = SseEmitter().apply {
            onCompletion { emitters.remove(memberSeq) }
            onTimeout { emitters.remove(memberSeq) }
        }
        emitters[memberSeq] = emitter
        return emitter
    }

    fun send(memberSeq: Long, message: String) {
        val data = SseEmitter.event()
            .name("notify")
            .data(message)
        emitters[memberSeq]?.send(data)
        emitters.remove(memberSeq)
    }
}
