package com.fantasy.ladbe.oauth.service

import com.fantasy.ladbe.oauth.dto.OAuthAttributes

import com.fantasy.ladbe.oauth.dto.OAuthAttributes.Companion.of
import com.fantasy.ladbe.service.UserService
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class CustomOAuth2UserService(
    val userService: UserService
) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2User: OAuth2User = DefaultOAuth2UserService() // 인가코드를 다시 요청하여 사용자 정보를 가져오는 역할
            .let {
                it.loadUser(userRequest)
            }

        val registerId: String =
            userRequest.clientRegistration.registrationId // 서비스의 이름을 명시 ex) kakao, google, github 등등..
        val usernameAttributeKey: String = userRequest.clientRegistration.providerDetails
            .userInfoEndpoint.userNameAttributeName // 소셜 로그인에서 사용하고 있는 고유 키 값 ex) id
        val oAuthAttributes: OAuthAttributes =
            of(registerId, usernameAttributeKey, oAuth2User.attributes) // 각 소셜 로그인을 통해 받은 정보

        userService.updateOrSave(
            kakaoCode = oAuthAttributes.code,
            email = oAuthAttributes.email,
            imagePath = oAuthAttributes.imagePath
        ).let {
            val collection: Collection<out GrantedAuthority> = AuthorityUtils.createAuthorityList("ROLE_USER") // 권한 주입
            return DefaultOAuth2User(collection, oAuthAttributes.attributes, oAuthAttributes.nameAttributeKey)
        }
    }
}
