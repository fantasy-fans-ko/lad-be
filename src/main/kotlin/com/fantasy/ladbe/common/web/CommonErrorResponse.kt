package com.fantasy.ladbe.common.web

import com.fantasy.ladbe.handler.exception.Exceptions
import java.time.Instant
import javax.servlet.http.HttpServletRequest

/**
 * @param errorCode 에러 코드
 * @param httpStatus 에러 상태에 대한 정보
 * @param httpMethod 사용된 method
 * @param message 에러에 대한 내용
 * @param path 에러가 발생
 * @param timestamp 에러가 발생된 시간
 */
data class CommonErrorResponse(
    val errorCode: String = "",
    val httpStatus: String = "",
    val httpMethod: String = "",
    val message: String = "",
    val path: String = "",
    val timestamp: Long = 0L,
) {
    companion object {
        fun error(
            exceptions: Exceptions,
            request: HttpServletRequest,
        ): CommonErrorResponse =
            CommonErrorResponse(
                errorCode = exceptions.code,
                httpStatus = exceptions.status.toString(),
                httpMethod = request.method,
                message = exceptions.message,
                path = request.requestURI,
                timestamp = Instant.now().epochSecond
            )
    }
}
