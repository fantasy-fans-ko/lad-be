package com.fantasy.ladbe.oauth.jwt

import com.fantasy.ladbe.common.web.CommonApiResponse.Companion.error
import com.fantasy.ladbe.dto.ErrorDto.Companion.toDto
import com.fantasy.ladbe.handler.exception.Exceptions.PERMISSION_DENIED
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

        error(errorDto = toDto(request, PERMISSION_DENIED))
        // TODO : 이 응답 내용을 어떻게 브라우저에게 내려줄 것인가.
    }
}
