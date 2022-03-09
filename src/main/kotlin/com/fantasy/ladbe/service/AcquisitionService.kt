package com.fantasy.ladbe.service

import com.fantasy.ladbe.repository.PlayerAcquisitionRepository
import org.springframework.stereotype.Service

@Service
class AcquisitionService(
    val acquisitionRepository: PlayerAcquisitionRepository,
)
