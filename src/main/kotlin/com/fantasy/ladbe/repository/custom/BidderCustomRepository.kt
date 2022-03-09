package com.fantasy.ladbe.repository.custom

import com.fantasy.ladbe.dto.BidderDto

interface BidderCustomRepository {
    fun selectBiddersByAuctionId(auctionId: Long): List<BidderDto.Response.BidderDetail>?
}
