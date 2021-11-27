package com.fantasy.ladbe.dto

import com.fantasy.ladbe.common.dto.PageParam
import com.fantasy.ladbe.model.User

class UserDto {
    class Request {
        data class UserId(val id: Long)
        data class UserPage(val pageable: PageParam)
        data class CreateUser(
            var kakaoCode: String,
            var kakaoImagePath: String,
            var kakaoEmail: String,
        )

    }

    class Response {
        data class UserDetail(
            var id: Long? = null,
            var nickname: String? = null,
            var kakaoCode: String? = null,
            var kakaoImagePath: String? = null,
            var kakaoEmail: String? = null,
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


    }


}
