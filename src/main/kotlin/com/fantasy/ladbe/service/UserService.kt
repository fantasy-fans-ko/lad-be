package com.fantasy.ladbe.service

import com.fantasy.ladbe.dto.UserDto
import com.fantasy.ladbe.model.User
import com.fantasy.ladbe.repository.UserRepository
import org.springframework.stereotype.Service


@Service
class UserService(val userRepository: UserRepository) {

    fun create(
        request: UserDto.Request.CreateUser,
    ) {
        val user = User(
            kakaoCode = request.kakaoCode,
            kakaoImagePath = request.kakaoImagePath,
            kakaoEmail = request.kakaoEmail
        )

        userRepository.save(user)
    }


    fun readOne(id : Long) : UserDto.Response.UserDetail?{
        return userRepository.selectById(id)?.let {
            UserDto.Response.UserDetail().entityToDto(it)
        }
    }

    fun readAll(): List<UserDto.Response.UserDetail>? {
        val users = userRepository.findAll()
        return users.map {it.toDto()}
    }
}