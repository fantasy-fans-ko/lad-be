package com.fantasy.ladbe.oauth.service

import com.fantasy.ladbe.handler.exception.BusinessException
import com.fantasy.ladbe.handler.exception.Exceptions
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

const val OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME: String = "oauth2_auth_request"
const val REDIRECT_URI_PARAM_COOKIE_NAME: String = "redirect_uri"
const val COOKIE_EXPIRE_SECONDS: Int = 100

/**
 * 리소스 서버에게 권한을 받아, Authorization Code 를 응답받기 위해 보내는 요청에서
 * 브라우저가 요청한 Redirect Url 의 정보를 쿠키로 저장시켜 응답을 받은 뒤에도 값을 유지시켜주는 클래스
 */
@Component
class HttpCookieOAuth2AuthorizationRequestRepository
    : AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    /**
     * 저장되어 있는 쿠키를, 역직렬화를 통해 OAuth2AuthorizationRequest 클래스로 변환하고 반환해줍니다.
     * @return OAuth2AuthorizationRequest 쿠키가 존재하지 않다면, 예외를 발생시켰습니다.
     */
    override fun loadAuthorizationRequest(request: HttpServletRequest): OAuth2AuthorizationRequest =
        CookieUtils.getCookie(request = request, name = OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME).map { cookie ->
            CookieUtils.deserialize(cookie, OAuth2AuthorizationRequest::class.java)
        }.orElseThrow { throw BusinessException(Exceptions.OAUTH2_REQUEST) }

    /**
     * 쿠키를 저장하는 함수입니다.
     * @param authorizationRequest 쿠키에 저장할 OAuth2 에서 생성된 요청 정보
     */
    override fun saveAuthorizationRequest(
        authorizationRequest: OAuth2AuthorizationRequest,
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        if (authorizationRequest == null) {
            CookieUtils.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME)
            CookieUtils.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME)
            return;
        }

        CookieUtils.addCookie(
            response,
            OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME,
            CookieUtils.serialize(authorizationRequest),
            COOKIE_EXPIRE_SECONDS
        )
        val redirectUriAfterLogin: String = request.getParameter(REDIRECT_URI_PARAM_COOKIE_NAME)

        if (redirectUriAfterLogin.isNotBlank()) {
            CookieUtils.addCookie(
                response,
                REDIRECT_URI_PARAM_COOKIE_NAME,
                redirectUriAfterLogin,
                COOKIE_EXPIRE_SECONDS
            )
        }
    }

    /**
     * OAuth2 요청을 삭제하는 메소드
     */
    override fun removeAuthorizationRequest(request: HttpServletRequest): OAuth2AuthorizationRequest? =
        this.loadAuthorizationRequest(request)


    /**
     * 쿠키를 삭제하는 메소드
     */
    fun removeAuthorizationRequestCookies(request: HttpServletRequest, response: HttpServletResponse) {
        CookieUtils.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME)
        CookieUtils.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME)
    }
}
