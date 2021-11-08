package com.fantasy.ladbe.model

import javax.persistence.*
import javax.persistence.GenerationType.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = IDENTITY)
    var id: Long = 0L,
    @NotNull @Column(name = "user_nickname")
    var userNickname : String = "",
    @NotNull @Column(name = "kakao_code")
    var kakaoCode : String = "",
    @NotNull @Column(name = "kakao_image_path")
    var kakaoImagePath : String = "",
    @NotNull @Column(name = "kakao_email")
    var kakaoEmail : String = "",
    @OneToMany(mappedBy = "user")
    var bidLogs : List<BidLog> = mutableListOf(),


    )
