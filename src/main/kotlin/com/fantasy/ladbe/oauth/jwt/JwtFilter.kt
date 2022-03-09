package com.fantasy.ladbe.oauth.jwt

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

const val AUTHORIZATION_HEADER: String = "Authorization"

class JwtFilter(
    private val jwtProvider: JwtProvider
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val jwt: String = resolveToken(request)

        if (jwt.isNotEmpty() && jwtProvider.validateToken(jwt, request)) {
            val authentication: Authentication = jwtProvider.parseJwt(jwt)
            SecurityContextHolder.getContext().authentication = authentication
        }

        chain.doFilter(request, response)
    }

    /**
     * 헤더에서 JWT 를 가져온 뒤, 유효성 검토를 위해 prefix 를 제거하는 작업을 하는 메소드
     * @param request 브라우저에게 받은 요청 정보
     * @return 헤더에 Authorization 이름으로 값이 있는지 확인한다.
     *         값이 없다면, "" -> 빈 문자열을 그대로 반환
     *         값이 있다면, 빈 값이 들어있는지 확인하고 Bearer 로 시작하는지 확인한다. 그리고 있다면 prefix 를 제거하고 반환
     */
    private fun resolveToken(request: HttpServletRequest): String {
        val token: String = request.getHeader(AUTHORIZATION_HEADER).let {
            it
        } ?: ""

        if (token.isNotEmpty() && token.startsWith("Bearer ")) {
            return token.substring(7)
        }

        return token
    }
}
