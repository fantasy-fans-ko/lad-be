package com.fantasy.ladbe.service

import com.fantasy.ladbe.dto.UserDto
import com.fantasy.ladbe.model.User
import com.fantasy.ladbe.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(val userRepository: UserRepository) {

    /**
     * 사용자의 정보를 업데이트 또는 회원가입을 해주는 메소드
     * param :  kakaoCode - 사용자 고유 값
     *          imagePath - 카카오 정보에 담겨져있는 프로필 사진의 경로
     *          email - 카카오 정보에 담겨있는 사용자 이메일
     * return : 카카오 고유 번호로 사용자 존재 여부를 확인을 하고 존재할 경우, 업데이트를 한다.
     *          null이 반환(사용자가 없음)이 된다면, 회원가입을 한다.
     *          마지막으로 Dto로 변환하여 반환한다.
     */
    @Transactional
    fun updateOrSave(
        kakaoCode: Long,
        imagePath: String,
        email: String
    ): UserDto.Response.UserDetail {
        val user: User = userRepository.selectByKakaoCode(kakaoCode)?.let {
            it.copy(
                id = it.id,
                kakaoCode = it.kakaoCode,
                kakaoImagePath = imagePath,
                kakaoEmail = email
            )
        }
            ?: User(
                kakaoCode = kakaoCode,
                kakaoEmail = email,
                kakaoImagePath = imagePath
            )
        return UserDto.Response.UserDetail()
            .entityToDto(userRepository.save(user))
    }

    fun readOne(id: Long): UserDto.Response.UserDetail? {
        return userRepository.selectById(id)?.let {
            UserDto.Response.UserDetail().entityToDto(it)
        }
    }

    fun readAll(): List<UserDto.Response.UserDetail>? {
        val users = userRepository.findAll()
        return users.map { it.toDto() }
    }
}
