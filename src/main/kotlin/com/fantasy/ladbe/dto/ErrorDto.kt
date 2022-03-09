package com.fantasy.ladbe.dto

import com.fantasy.ladbe.handler.exception.Exceptions
import javax.servlet.http.HttpServletRequest

/**
 * @param errorCode 에러 코드
 * @param httpStatus 에러 상태에 대한 정보
 * @param httpMethod 사용된 method
 * @param message 에러 정보의 내용
 * @param path 에러가 발생
 */
data class ErrorDto(
    val errorCode: String = "",
    val httpStatus: String = "",
    val httpMethod: String = "",
    val message: String = "",
    val path: String = "",
) {
    companion object {
        fun toDto(
            request: HttpServletRequest,
            exceptions: Exceptions
        ): ErrorDto {
            return ErrorDto(
                httpStatus = exceptions.status.toString(),
                httpMethod = request.method,
                path = request.requestURI,
                message = exceptions.message,
                errorCode = exceptions.code,
            )
        }
    }
}
