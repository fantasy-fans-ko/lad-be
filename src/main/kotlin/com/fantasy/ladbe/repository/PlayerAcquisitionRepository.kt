package com.fantasy.ladbe.repository

import com.fantasy.ladbe.model.PlayerAcquisition
import org.springframework.data.jpa.repository.JpaRepository

interface PlayerAcquisitionRepository : JpaRepository<PlayerAcquisition, Long> {
}
