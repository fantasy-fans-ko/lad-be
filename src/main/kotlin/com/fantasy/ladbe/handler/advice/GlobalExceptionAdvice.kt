package com.fantasy.ladbe.handler.advice

import com.fantasy.ladbe.common.web.CommonErrorResponse
import com.fantasy.ladbe.handler.exception.BusinessException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class GlobalExceptionAdvice : ResponseEntityExceptionHandler() {

    @ExceptionHandler(BusinessException::class)
    fun businessExceptionHandler(
        businessException: BusinessException,
        request: HttpServletRequest
    ): ResponseEntity<CommonErrorResponse> =
        ResponseEntity(
            CommonErrorResponse.error(
                exceptions = businessException.exceptions,
                request = request
            ),
            businessException.exceptions.status
        )
}
