package com.fantasy.ladbe.service

import com.fantasy.ladbe.repository.BidLogRepository
import org.springframework.stereotype.Service

@Service
class BidLogService(
    val bidLogRepository: BidLogRepository,
)
