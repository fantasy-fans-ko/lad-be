package com.fantasy.ladbe.service

import com.fantasy.ladbe.repository.AuctionRepository
import org.springframework.stereotype.Service

@Service
class AuctionService(
    val auctionRepository: AuctionRepository,
)
