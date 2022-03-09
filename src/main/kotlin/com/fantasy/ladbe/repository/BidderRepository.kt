package com.fantasy.ladbe.repository

import com.fantasy.ladbe.model.Bidder
import com.fantasy.ladbe.repository.custom.BidderCustomRepository
import org.springframework.data.jpa.repository.JpaRepository

interface BidderRepository : JpaRepository<Bidder, Long>, BidderCustomRepository
