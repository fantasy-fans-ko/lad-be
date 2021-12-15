package com.fantasy.ladbe.repository.custom

import com.fantasy.ladbe.dto.UserDto
import com.fantasy.ladbe.oauth.jwt.JwtProvider
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.Authentication
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class KakaoLoginTest @Autowired constructor(
    val jwtProvider: JwtProvider,
) {

    @Test
    fun jwtTest() {
        val dummyOAuth2Detail : UserDto.Response.OAuth2UserDetail = UserDto.Response.OAuth2UserDetail(
            kakaoImagePath = "",
            kakaoEmail = "dygks8557@nate.com",
            kakaoName = "요한",
            role = "ROLE_USER"
        )

        val dummyJwt = jwtProvider.create(dummyOAuth2Detail)
        val authentication : Authentication = jwtProvider.parseJwt(dummyJwt)

        println(authentication)
    }
}
