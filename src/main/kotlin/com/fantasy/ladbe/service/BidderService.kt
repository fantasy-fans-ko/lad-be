package com.fantasy.ladbe.service

import com.fantasy.ladbe.repository.BidderRepository
import org.springframework.stereotype.Service

@Service
class BidderService(
    val bidderRepository: BidderRepository
)
