package com.fantasy.ladbe.model

import javax.persistence.*
import javax.persistence.GenerationType.*

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0L,
    @Column(name = "kakao_code", length = 256)
    val kakaoCode : String = "",
    @Column(name = "kakao_image_path", length = 256)
    val kakaoImagePath : String = "",
    @Column(name = "kakao_email", length = 49)
    val kakaoEmail : String = "",
    )
