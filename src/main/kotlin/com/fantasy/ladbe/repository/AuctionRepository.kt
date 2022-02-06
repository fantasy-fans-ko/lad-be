package com.fantasy.ladbe.repository

import com.fantasy.ladbe.model.Auction
import org.springframework.data.jpa.repository.JpaRepository

interface AuctionRepository : JpaRepository<Auction, Long> {
}
