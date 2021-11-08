package com.fantasy.ladbe.model

import javax.persistence.*
import javax.persistence.GenerationType.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0L,
    @NotNull @Column(name = "nickname")
    val nickname : String = "",
    @NotNull @Column(name = "kakao_code")
    val kakaoCode : String = "",
    @NotNull @Column(name = "kakao_image_path")
    val kakaoImagePath : String = "",
    @NotNull @Column(name = "kakao_email")
    val kakaoEmail : String = "",
    )
