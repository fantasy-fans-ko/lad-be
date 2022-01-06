package com.fantasy.ladbe.handler.advice

import com.fantasy.ladbe.common.web.ErrorResponse
import com.fantasy.ladbe.controller.PlayerController
import com.fantasy.ladbe.handler.exception.Exceptions.INVALID_PARAM
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import javax.servlet.http.HttpServletRequest

@ControllerAdvice(basePackageClasses = [PlayerController::class])
class PlayerControllerAdvice {

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun methodArgumentTypeMismatchException(
        e: MethodArgumentTypeMismatchException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {

        val errorResponse = ErrorResponse().toErrorResponse(request, INVALID_PARAM)

        return ResponseEntity.badRequest().body(errorResponse)
    }
}
