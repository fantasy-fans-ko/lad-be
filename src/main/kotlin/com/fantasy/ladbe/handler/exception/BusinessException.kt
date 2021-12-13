package com.fantasy.ladbe.handler.exception

import org.springframework.web.bind.annotation.ExceptionHandler

class BusinessException(
    val exceptions: Exceptions
) : RuntimeException() {

}
