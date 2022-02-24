package com.fantasy.ladbe.oauth.jwt

import com.fantasy.ladbe.common.web.CommonErrorResponse.Companion.error
import com.fantasy.ladbe.handler.exception.Exceptions
import com.google.gson.Gson
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * JWT 의 유효성 검사를 했을 때, 나온 에러들을 처리하는 클래스
 */
@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        // 각 에러 코드에 맞는 곳으로 매핑해서 에러 내용을 브라우저에게 전송
        when (request.getAttribute("exception").toString()) {
            Exceptions.JWT_WRONG_TYPE_TOKEN.code -> setResponse(response, request, Exceptions.JWT_WRONG_TYPE_TOKEN)
            Exceptions.JWT_EXPIRED_TOKEN.code -> setResponse(response, request, Exceptions.JWT_EXPIRED_TOKEN)
            Exceptions.JWT_UNSUPPORTED_TOKEN.code -> setResponse(response, request, Exceptions.JWT_UNSUPPORTED_TOKEN)
            Exceptions.JWT_WRONG_TOKEN.code -> setResponse(response, request, Exceptions.JWT_WRONG_TOKEN)
            Exceptions.JWT_NULL_TOKEN.code -> setResponse(response, request, Exceptions.JWT_NULL_TOKEN)
            else -> setResponse(response, request, Exceptions.JWT_UNKNOWN_ERROR)
        }
    }

    /**
     * 브라우저에게 보내줄 에러 내용을 작성하는 메소드
     * @param exceptions Enum 형식으로 작성한 데이터 타입
     */
    private fun setResponse(
        response: HttpServletResponse,
        request: HttpServletRequest,
        exceptions: Exceptions
    ) {
        response.contentType = "application/json"
        response.characterEncoding = "utf-8"

        val gson = Gson().toJson(
            error(exceptions = exceptions, request = request)
        )
        response.writer.write(gson)
    }
}
