package com.fantasy.ladbe.oauth.mapper

import com.fantasy.ladbe.api.dto.UserDto
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Component

@Component
class UserRequestMapper {
    public fun toDto(oAuth2User: OAuth2User): UserDto {
        val attributes = oAuth2User.attributes
        return UserDto(
            name = attributes["name"] as String,
            email = attributes["email"] as String,
            pictue = attributes["picture"] as String
        )
    }
}