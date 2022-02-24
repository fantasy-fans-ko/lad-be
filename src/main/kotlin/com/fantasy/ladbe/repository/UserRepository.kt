package com.fantasy.ladbe.repository

import com.fantasy.ladbe.model.User
import com.fantasy.ladbe.repository.custom.UserCustomRepository
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>, UserCustomRepository {
    fun findByKakaoCode(kakaoCode: Long) : User?
}
