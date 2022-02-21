package com.fantasy.ladbe.oauth.handler

import com.fantasy.ladbe.dto.UserDto
import com.fantasy.ladbe.oauth.jwt.JwtProvider
import com.fantasy.ladbe.oauth.service.CookieUtils
import com.fantasy.ladbe.oauth.service.HttpCookieOAuth2AuthorizationRequestRepository
import com.fantasy.ladbe.oauth.service.REDIRECT_URI_PARAM_COOKIE_NAME
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class OAuth2SuccessHandler(
    val jwtProvider: JwtProvider,
    val httpCookieOAuth2AuthorizationRequestRepository: HttpCookieOAuth2AuthorizationRequestRepository
) : SimpleUrlAuthenticationSuccessHandler() {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val oAuth2Dto = UserDto.Response.OAuth2UserDetail().oAuth2ToDto(authentication)
        val redirectUrl = determineTargetUrl(request, response, oAuth2Dto)

        if (response.isCommitted) { // 응답이 commit 되었는지의 여부를 확인
            print("이미 커밋이 되었다. $redirectUrl")
            return;
        }

        clearAuthenticationAttributes(request, response)
        redirectStrategy.sendRedirect(request, response, redirectUrl)
    }

    /**
     * 브라우저에게 받은 Redirect Uri 정보와 JWT 를 하나로 뭉치는 작업하는 메소드
     * @param oAuth2Dto 커스텀한 OAuth2 의 정보들이 담겨있는 객체
     */
    protected fun determineTargetUrl(
        request: HttpServletRequest,
        response: HttpServletResponse,
        oAuth2Dto: UserDto.Response.OAuth2UserDetail
    ): String {
        val redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME).map(Cookie::getValue)

        if (!redirectUri.isPresent)
            throw RuntimeException("익셉션입니다.")

        val targetUrl = redirectUri.orElse(defaultTargetUrl)
        val token = jwtProvider.create(oAuth2Dto)

        return UriComponentsBuilder.fromUriString(targetUrl)
            .queryParam("token", token).build()
            .toUriString()
    }

    /**
     * 로그인 시도에 대한 세션을 초기화하는 메소드
     */
    protected fun clearAuthenticationAttributes(request: HttpServletRequest, response: HttpServletResponse) {
        super.clearAuthenticationAttributes(request)
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response)
    }
}
