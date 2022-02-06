package com.fantasy.ladbe.oauth.jwt

import com.fantasy.ladbe.common.web.CommonErrorResponse.Companion.error
import com.fantasy.ladbe.handler.exception.Exceptions.PERMISSION_DENIED
import com.google.gson.Gson
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
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
        response.contentType = "application/json"
        response.characterEncoding = "utf-8"

        val gson = Gson().toJson(
            error(exceptions = PERMISSION_DENIED, request = request)
        )
        response.writer.write(gson)
    }
}
