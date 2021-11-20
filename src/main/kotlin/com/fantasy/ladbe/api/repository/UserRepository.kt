package com.fantasy.ladbe.api.repository

import com.fantasy.ladbe.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByKakaoEmail(email : String) : User?
}