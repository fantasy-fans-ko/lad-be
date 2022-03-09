package com.fantasy.ladbe.oauth.handler

import com.fantasy.ladbe.dto.UserDto
import com.fantasy.ladbe.handler.exception.BusinessException
import com.fantasy.ladbe.handler.exception.Exceptions
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
    val httpCookieOAuth2AuthorizationRequestRepository: HttpCookieOAuth2AuthorizationRequestRepository,
) : SimpleUrlAuthenticationSuccessHandler() {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication,
    ) {
        val oAuth2Dto = UserDto.Response.OAuth2UserDetail().oAuth2ToDto(authentication)
        val redirectUrl = determineTargetUrl(request, response, oAuth2Dto)

        /**
         * isCommitted 메소드의 역할
         * 응답이 이미 클라이언트에 커밋되었는지 여부를 확인합니다(응답 내용을 쓰기 위해 서블릿 출력 스트림이 열렸음을 의미합니다).
         * 커밋된 응답은 HTTP 상태 및 헤더를 보유하며 수정할 수 없습니다. 헤더와 상태가 콘텐츠 자체보다 먼저 커밋되기 때문에
         * 이 경우 응답 콘텐츠가 아직 작성되지 않았음을 주목하는 것도 중요합니다.
         */
        if (response.isCommitted)
            throw BusinessException(Exceptions.RESPONSE_NOT_COMMITTED)

        clearAuthenticationAttributes(request, response)
        redirectStrategy.sendRedirect(request, response, redirectUrl)
    }

    /**
     * 브라우저에게 받은 Redirect Uri 정보와 JWT 를 하나로 뭉치는 작업을 하는 메소드
     * @param oAuth2Dto 커스텀한 OAuth2 의 정보들이 담겨있는 객체
     */
    protected fun determineTargetUrl(
        request: HttpServletRequest,
        response: HttpServletResponse,
        oAuth2Dto: UserDto.Response.OAuth2UserDetail,
    ): String {
        val redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME).map(Cookie::getValue)

        if (!redirectUri.isPresent)
            throw BusinessException(Exceptions.OAUTH2_REDIRECT_URL)

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
