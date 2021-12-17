package com.fantasy.ladbe.service

import com.fantasy.ladbe.dto.PlayerDto
import com.fantasy.ladbe.repository.PlayerRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class PlayerService(
    val playerRepository: PlayerRepository,
) {
    fun readOne(id: Long): PlayerDto.Response.PlayerDetail {
        return playerRepository.selectById(id)?.let {
            PlayerDto.Response.PlayerDetail().entityToDto(it)
        } ?: throw Exception()
        // TODO: 2021/12/17 Exception 부분 kakao-login PR 합친 이후 수정 예정
    }

    fun readAll(): List<PlayerDto.Response.PlayerDetail> {
        return playerRepository.selectAll().map {
            PlayerDto.Response.PlayerDetail().entityToDto(it)
        }
    }

    fun readPage(request: PlayerDto.Request.PlayerPage): Page<PlayerDto.Response.PlayerDetail> {
        return playerRepository.selectPlayersByPaging(request).map {
            PlayerDto.Response.PlayerDetail().entityToDto(it)
        }
    }
}
