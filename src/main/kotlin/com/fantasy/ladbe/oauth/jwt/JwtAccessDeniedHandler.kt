package com.fantasy.ladbe.oauth.jwt

import com.fantasy.ladbe.common.web.ErrorResponse
import com.fantasy.ladbe.handler.exception.Exceptions
import com.google.gson.Gson
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import java.time.Instant
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 권한이 없는 사용자가 접근을 할 경우, 에러 내용 전송
 */
@Component
class JwtAccessDeniedHandler : AccessDeniedHandler {

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        val errorResponse = ErrorResponse(
            resultCode = Exceptions.PERMISSION_DENIED.code,
            httpStatus = HttpServletResponse.SC_UNAUTHORIZED.toString(),
            httpMethod = request.method,
            message = Exceptions.PERMISSION_DENIED.message.toString(),
            path = request.requestURI,
            timestamp = Instant.now().epochSecond,
        )

        val gson = Gson().toJson(errorResponse)
        response.contentType = "application/json"
        response.characterEncoding = "utf-8"
        response.writer.write(gson)
    }
}
