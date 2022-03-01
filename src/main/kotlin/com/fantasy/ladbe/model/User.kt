package com.fantasy.ladbe.model

import com.fantasy.ladbe.dto.UserDto
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0L,
    @Column(name = "kakao_code")
    val kakaoCode: Long = 0L,
    @Column(name = "kakao_image_path")
    val kakaoImagePath: String = "", // 이미지의 사이즈는 110 * 110 or 100 * 100 px
    @Column(name = "kakao_email")
    val kakaoEmail: String = "",
) {
    fun toDto(): UserDto.Response.UserDetail {
        return UserDto.Response.UserDetail(
            id = id,
            kakaoImagePath = kakaoImagePath,
            kakaoEmail = kakaoEmail
        )
    }
}
