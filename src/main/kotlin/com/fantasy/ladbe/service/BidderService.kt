package com.fantasy.ladbe.service

import com.fantasy.ladbe.dto.BidderDto
import com.fantasy.ladbe.handler.exception.BusinessException
import com.fantasy.ladbe.handler.exception.Exceptions
import com.fantasy.ladbe.repository.BidderRepository
import org.springframework.stereotype.Service

@Service
class BidderService(
    val bidderRepository: BidderRepository,
) {
    fun readAllByAuctionId(auctionId: Long): List<BidderDto.Response.BidderDetail> =
        bidderRepository.selectBiddersByAuctionId(auctionId)
            ?: throw BusinessException(Exceptions.AUCTION_NOT_FOUND)
}
