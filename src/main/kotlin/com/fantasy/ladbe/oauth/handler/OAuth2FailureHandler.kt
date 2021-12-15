package com.fantasy.ladbe.oauth.handler

import com.fantasy.ladbe.common.log.logger
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.RedirectStrategy
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class OAuth2FailureHandler(
    val redirectStrategy: RedirectStrategy = DefaultRedirectStrategy()
) : AuthenticationFailureHandler {

    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        when(exception) {
            is BadCredentialsException -> logger().info("$exception")
            is InternalAuthenticationServiceException -> logger().info("$exception")
            /**
             * 유저의 정보가 없을 때, 회원가입 유도를 하려고 함.
             * 회원가입 버튼을 누르면 바로 회원가입이 되도록 하게함.
             * -> 그러면 회원의 정보를 어떻게 받아올 것인가? (클릭했을 때, 회원의 정보가 들어가야댐. 세션으로 관리를 해야하는가?)
             * 결론 : 로그인을 함과 동시에 회원가입이 되도록
             */
            is UsernameNotFoundException -> {
                logger().info("$exception")
                with(redirectStrategy) { sendRedirect(request, response, "http://localhost:3000/connect") }
            }
        }
    }
}
