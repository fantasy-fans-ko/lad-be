package com.fantasy.ladbe.model

import javax.persistence.*
import javax.persistence.GenerationType.*

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0L,
    @Column(length = 16)
    var nickname : String = "",
    @Column(name = "kakao_code", length = 256)
    val kakaoCode : String = "",
    @Column(name = "kakao_image_path", length = 256)
    var kakaoImagePath : String = "",
    @Column(name = "kakao_email", length = 49)
    val kakaoEmail : String = "",
    ){

    fun update(nickname: String,picture: String) : User{
        this.nickname = nickname
        this.kakaoImagePath = picture

        return this
    }
}
