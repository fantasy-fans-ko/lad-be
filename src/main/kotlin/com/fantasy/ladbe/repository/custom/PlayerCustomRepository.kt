package com.fantasy.ladbe.repository.custom

import com.fantasy.ladbe.model.Player

interface PlayerCustomRepository {
    fun selectAll(): List<Player>
}