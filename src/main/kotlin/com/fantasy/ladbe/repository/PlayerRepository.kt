package com.fantasy.ladbe.repository

import com.fantasy.ladbe.model.Player
import com.fantasy.ladbe.repository.custom.PlayerCustomRepository
import org.springframework.data.jpa.repository.JpaRepository

interface PlayerRepository : JpaRepository<Player, Long>, PlayerCustomRepository
