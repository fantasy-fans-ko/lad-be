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
     * return : findOneByKakaoCode 메소드에서 반환값이 null이 아니라면, 업데이트를 한다.
     *          null이 반환(사용자가 없음)이 된다면, 회원가입을 한다.
     *          마지막으로 Dto로 변환하여 반환한다.
     */
    @Transactional
    fun updateOrSave(
        kakaoCode: Long,
        imagePath: String,
        email: String
    ): UserDto.Response.UserDetail {
        val user: User = findOneByKakaoCode(kakaoCode)?.let {
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

    /**
     * 소셜에서 사용되는 고유 값을 이용해 한명의 이용자 찾기
     * param :
     *      kakaoCode - 사용자 고유 번호
     * return : 이용자가 존재한다면, User Entity를 반환한다.
     *          없다면, null을 반환한다.
     */
    private fun findOneByKakaoCode(kakaoCode: Long): User? {
        return userRepository.selectByKakaoCode(kakaoCode)
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
