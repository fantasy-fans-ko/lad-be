package com.fantasy.ladbe.dto

import com.fantasy.ladbe.common.dto.PageParam
import com.fantasy.ladbe.model.User
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User
import java.util.stream.Collectors

class UserDto {
    class Request {
        data class UserId(val id: Long)
        data class UserPage(val pageable: PageParam)
    }

    class Response {
        data class UserDetail(
            val id: Long = 0L,
            val kakaoCode: Long = 0L,
            val kakaoImagePath: String = "",
            val kakaoEmail: String = "",
        ) {
            fun entityToDto(user: User): UserDetail {
                return UserDetail(
                    id = user.id,
                    kakaoCode = user.kakaoCode,
                    kakaoImagePath = user.kakaoImagePath,
                    kakaoEmail = user.kakaoEmail
                )
            }
        }

        /**
         * OAuth2 로그인에 성공했을 때,
         * 로그인한 사용자에 대한 정보를 원할하게 사용하기 위한 Dto
         */
        data class OAuth2UserDetail(
            val kakaoImagePath: String = "",
            val kakaoEmail: String = "",
            val kakaoName: String = "",
            val role: String = "",
        ) {
            fun oAuth2ToDto(authentication: Authentication): OAuth2UserDetail {
                val oAuth2User: OAuth2User = authentication.principal as OAuth2User
                val kakaoAccount: Map<String, Any> = oAuth2User.attributes["kakao_account"] as Map<String, Any>
                val profile: Map<String, Any> = kakaoAccount["profile"] as Map<String, Any>

                return OAuth2UserDetail(
                    kakaoName = profile["nickname"].toString(),
                    kakaoEmail = kakaoAccount["email"].toString(),
                    kakaoImagePath = profile["thumbnail_image_url"].toString(),
                    role = authentication.authorities.stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(",")) // 권한의 정보를 가져와 ","를 기준으로 문자열로 나열함
                )
            }
        }
    }
}
