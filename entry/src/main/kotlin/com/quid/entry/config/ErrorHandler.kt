package com.quid.entry.config

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.async.AsyncRequestTimeoutException
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RestControllerAdvice
class ErrorHandler {

    @ExceptionHandler(AsyncRequestTimeoutException::class)
    fun sseTimeoutException(e: AsyncRequestTimeoutException): SseEmitter? {
        return null
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<Error> =
        ResponseEntity<Error>(
            Error { ex.message ?: "Unknown Error" },
            HttpStatusSelector(ex)
        ).also { ex.printStackTrace() }
}

interface HttpStatusSelector {
    operator fun invoke(ex: Exception): HttpStatus

    class HttpStatusSelectorImpl : HttpStatusSelector {
        override fun invoke(ex: Exception): HttpStatus =
            when (ex) {
                is IllegalArgumentException -> HttpStatus.BAD_REQUEST
                is NoSuchElementException -> HttpStatus.NOT_FOUND
                is IllegalStateException -> HttpStatus.CONFLICT
                is UnauthorizedException -> HttpStatus.UNAUTHORIZED
                else -> HttpStatus.INTERNAL_SERVER_ERROR
            }
    }

    companion object : HttpStatusSelector by HttpStatusSelectorImpl()
}

class UnauthorizedException(message: String) : RuntimeException(message)

data class Error(
    val message: String
) {
    val status: String = "ERROR"
    val timeStamp: String
        get() = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
}

fun Error(message: () -> String): Error = Error(message())
