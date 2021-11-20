package com.fantasy.ladbe.oauth.handler

import com.fantasy.ladbe.api.repository.UserRepository
import com.fantasy.ladbe.model.User
import com.fantasy.ladbe.oauth.mapper.UserRequestMapper
import com.fantasy.ladbe.oauth.service.TokenService
import com.fantasy.ladbe.oauth.token.AuthToken
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class OAuth2SuccessHandler(
    private val tokenService: TokenService,
    private val userRequestMapper: UserRequestMapper,
    private val objectMapper: ObjectMapper,
    private val userRepository: UserRepository
) : AuthenticationSuccessHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication,
    ) {
        val oAuth2User = authentication.principal as OAuth2User
        val userDto = userRequestMapper.toDto(oAuth2User)

        val token: AuthToken = tokenService.generateToken(userDto.email, "USER")
        log.info("{}", token)




        // 성공 유저 디비 저장
        val user = userRepository.findByKakaoEmail(userDto.email) ?: userRepository.save(
            User(
                nickname = userDto.name,
                kakaoEmail = userDto.email,
                kakaoImagePath = userDto.pictue,
                kakaoCode = "SampleCode"
            ))
        user.update(nickname = userDto.name, picture = userDto.pictue)





        writeTokenResponse(response, token)


    }

    @Throws(IOException::class)
    private fun writeTokenResponse(response: HttpServletResponse, token: AuthToken) {
        response.contentType = "text/html;charset=UTF-8"
        response.addHeader("Auth", token.token)
        response.addHeader("Refresh", token.refreshToken)
        response.contentType = "application/json;charset=UTF-8"
        val writer = response.writer
        writer.println(objectMapper.writeValueAsString(token))
        writer.flush()
    }

}


