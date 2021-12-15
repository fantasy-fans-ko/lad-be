package com.fantasy.ladbe.handler.advice

import com.fantasy.ladbe.common.web.ErrorResponse
import com.fantasy.ladbe.handler.exception.Exceptions.SERVICE_ERROR
import com.fantasy.ladbe.controller.PlayerController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.Instant
import javax.servlet.http.HttpServletRequest


@RestControllerAdvice(basePackageClasses = [PlayerController::class])
class PlayerControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            resultCode = SERVICE_ERROR.code,
            httpStatus = HttpStatus.BAD_REQUEST.value().toString(),
            httpMethod = request.method,
            message = SERVICE_ERROR.message.toString(),
            path = request.requestURI,
            timestamp = Instant.now().epochSecond
        )

        return ResponseEntity.badRequest().body(errorResponse)
    }
}
