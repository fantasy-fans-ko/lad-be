package com.fantasy.ladbe.handler.advice

import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException::class)
    fun usernameNotFoundException(e: MethodArgumentNotValidException, request: HttpServletRequest) {
        println("usernameException")
    }
}
