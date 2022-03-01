package com.fantasy.ladbe.service

import com.fantasy.ladbe.dto.UserDto
import com.fantasy.ladbe.handler.exception.BusinessException
import com.fantasy.ladbe.handler.exception.Exceptions.USER_NOT_FOUND
import com.fantasy.ladbe.handler.exception.Exceptions.USER_NOT_LOGIN
import com.fantasy.ladbe.model.User
import com.fantasy.ladbe.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    val userRepository: UserRepository
) {
    /**
     * 사용자의 정보를 업데이트 또는 회원가입을 해주는 메소드
     * @param kakaoCode 사용자 고유 값
     * @param imagePath 카카오 정보에 담겨져있는 프로필 사진의 경로
     * @param email 카카오 정보에 담겨있는 사용자 이메일
     * @return 카카오 고유 번호로 사용자 존재 여부를 확인을 하고 존재할 경우, 업데이트를 한다.
     *         null이 반환(사용자가 없음)이 된다면, 회원가입을 한다.
     *         마지막으로 Dto로 변환하여 반환한다.
     */
    @Transactional
    fun updateOrSave(
        kakaoCode: Long,
        imagePath: String,
        email: String
    ): UserDto.Response.UserDetail {
        val user: User = userRepository.findByKakaoCode(kakaoCode).orElseGet {
            User(
                kakaoCode = kakaoCode,
                kakaoEmail = email,
                kakaoImagePath = imagePath
            )
        }.let {
            it.copy(
                id = it.id,
                kakaoCode = it.kakaoCode,
                kakaoImagePath = imagePath,
                kakaoEmail = email
            )
        }
        return userRepository.save(user).toDto()
    }

    /**
     * 사용자 고유 번호를 통해, 사용자 정보 가져오기
     * @param id 사용자가 가지고 있는 고유 번호 (유니크)
     * @return UserDetail DTO 로 변환하여 반환합니다.
     */
    fun readOneById(id: Long): UserDto.Response.UserDetail =
        userRepository.findById(id).orElseThrow { throw BusinessException(USER_NOT_FOUND) }
            .let { it.toDto() }

    /**
     * 카카오 고유 번호를 통해, 사용자 정보 가져오기
     * @param kakaoCode 사용자가 가지고 있는 카카오 고유 번호
     * @return UserDetail DTO 로 변환하여 반환합니다.
     */
    fun readOneByKakaoCode(kakaoCode: Long): UserDto.Response.UserDetail =
        userRepository.findByKakaoCode(kakaoCode).orElseThrow { throw BusinessException(USER_NOT_FOUND) }
            .let { it.toDto() }

    /**
     * Spring Security 를 활용하여 로그인한 사용자의 정보 가져오기
     * 만약, 로그인의 정보가 없다면, 예외처리
     * @return 사용자 정보를 가지고 있는 객체를 반환해줍니다.
     */
    fun readOneByContext(): UserDto.Response.UserDetail {
        val authentication = SecurityContextHolder.getContext().authentication

        if (authentication.principal.equals("anonymousUser"))
            throw BusinessException(USER_NOT_LOGIN)

        return readOneByKakaoCode(authentication.name.toLong())
    }

    /**
     * 모든 사용자 정보를 가져오기
     */
    fun readAll(): List<UserDto.Response.UserDetail> =
        userRepository.findAll().map { it.toDto() }
}
