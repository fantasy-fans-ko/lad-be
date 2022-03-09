package com.fantasy.ladbe.repository.custom

import com.fantasy.ladbe.dto.PlayerDto
import com.fantasy.ladbe.model.Player
import org.springframework.data.domain.Page

interface PlayerCustomRepository {
    fun selectPlayersByPaging(playerPage: PlayerDto.Request.PlayerPage): Page<Player>
}
