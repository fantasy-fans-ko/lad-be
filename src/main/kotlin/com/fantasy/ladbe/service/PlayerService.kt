package com.fantasy.ladbe.service

import com.fantasy.ladbe.dto.PlayerDto
import com.fantasy.ladbe.handler.exception.BusinessException
import com.fantasy.ladbe.handler.exception.Exceptions.USER_NOT_FOUND
import com.fantasy.ladbe.repository.PlayerRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class PlayerService(
    val playerRepository: PlayerRepository,
) {
    fun readOne(id: Long): PlayerDto.Response.PlayerDetail =
        playerRepository.selectById(id)?.let {
            it.toDto()
        } ?: throw BusinessException(USER_NOT_FOUND)

    fun readAll(): List<PlayerDto.Response.PlayerDetail> =
        playerRepository.selectAll().map {
            it.toDto()
        }

    fun readPage(
        request: PlayerDto.Request.PlayerPage
    ): Page<PlayerDto.Response.PlayerDetail> =
        playerRepository.selectPlayersByPaging(request).map {
            it.toDto()
        }
}
