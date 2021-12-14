package com.fantasy.ladbe.oauth.handler

import com.fantasy.ladbe.dto.UserDto
import com.fantasy.ladbe.oauth.jwt.JwtProvider
import net.minidev.json.JSONObject
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.WebAttributes
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.security.web.savedrequest.RequestCache
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class OAuth2SuccessHandler(
    val requestCache: RequestCache = HttpSessionRequestCache(),
    val jwtProvider: JwtProvider,
) : AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        clearAuthenticationAttributes(request)

        val oAuth2Dto = UserDto.Response.OAuth2UserDetail().oAuth2ToDto(authentication)

        writeResponseData(response, oAuth2Dto)
    }

    /**
     * 로그인 시도에 대한 세션을 초기화하는 메소드
     */
    private fun clearAuthenticationAttributes(request: HttpServletRequest) {
        request.getSession(false).let {
            it.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION)
        }
    }

    /**
     * f/e 쪽으로 전달할 데이터 작성
     * param :  response - 응답에 대한 정보
     *          oAuth2Dto - 편의를 위해 카카오 정보를 매핑해 생성한 Dto 객체
     *          authentication - 권한 정보를 위해 넣었지만, oAuth2Dto 객체에 하나로 밀어넣어도 될 듯.
     */
    private fun writeResponseData(
        response: HttpServletResponse,
        oAuth2Dto: UserDto.Response.OAuth2UserDetail,
//        authentication: Authentication
    ) {
        val accessToken: String = jwtProvider.create(oAuth2Dto) // jwt 생성

        // f/e에게 보내줄 데이터 json 형식으로 작성
        val data = JSONObject()
        data["Authorization"] = accessToken
        data["kakaoImagePath"] = oAuth2Dto.kakaoImagePath
        data["kakaoName"] = oAuth2Dto.kakaoName
        data["kakaoEmail"] = oAuth2Dto.kakaoEmail
        data["redirectUrl"] = "http://localhost:3000/"

        response.contentType = "application/json"
        response.characterEncoding = "utf-8"
        response.writer.write(data.toJSONString()) // body 로 전송
    }
}
