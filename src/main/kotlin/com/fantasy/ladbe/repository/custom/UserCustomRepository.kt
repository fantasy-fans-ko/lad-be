package com.fantasy.ladbe.repository.custom

import com.fantasy.ladbe.model.User

interface UserCustomRepository {
    fun selectById(id : Long) : User?
}

