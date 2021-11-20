package com.fantasy.ladbe.oauth.service

import org.slf4j.LoggerFactory
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.util.*


@Service
class CustomOAuth2UserService : OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private val log = LoggerFactory.getLogger(javaClass)


    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {


        val oAuth2UserService: OAuth2UserService<OAuth2UserRequest, OAuth2User> = DefaultOAuth2UserService()

        val oAuth2User: OAuth2User = oAuth2UserService.loadUser(userRequest)
        val registrationId: String = userRequest.clientRegistration.registrationId
        val userNameAttributeName =
            userRequest.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName

        val oAuth2Attribute: OAuth2Attribute = OAuth2Attribute.of(registrationId, userNameAttributeName, oAuth2User.attributes)

        log.info("{}", oAuth2Attribute)

        var memberAttribute = oAuth2Attribute.convertToMap()

        return DefaultOAuth2User(Collections.singleton(SimpleGrantedAuthority("ROLE_USER")),memberAttribute, "email")

    }
}


class OAuth2Attribute(
    val attributes: Map<String, Any>,
    val attributeKey: String,
    val name: String,
    val email: String,
    val picture: String,
) {
    companion object {

        fun of(provider: String, attributeKey: String, attributes: Map<String, Any>): OAuth2Attribute {
            when (provider) {
                "kakao" -> return ofKakao("email", attributes)
                else -> throw RuntimeException()
            }
        }

        private fun ofKakao(
            attributeKey: String,
            attributes: Map<String, Any>,
        ): OAuth2Attribute {
            val kakaoAccount = attributes["kakao_account"] as Map<String, Any>
            val kakaoProfile = kakaoAccount["profile"] as Map<String, Any>
            return OAuth2Attribute(
                attributes,
                attributeKey,
                kakaoProfile["nickname"] as String,
                kakaoAccount["email"] as String,
                kakaoProfile["profile_image_url"] as String,
            )
        }


    }

    fun convertToMap(): Map<String, Any> {
        var map: MutableMap<String, Any> = mutableMapOf()
        map.put("id", attributeKey)
        map.put("key", attributeKey)
        map.put("name", name)
        map.put("email", email)
        map.put("picture", picture)

        return map
    }


}
