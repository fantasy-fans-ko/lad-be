package com.fantasy.ladbe.socket.service

import com.fantasy.ladbe.handler.exception.BusinessException
import com.fantasy.ladbe.handler.exception.Exceptions.*
import com.fantasy.ladbe.model.BidLog
import com.fantasy.ladbe.repository.*
import com.fantasy.ladbe.socket.dto.BidLogSocketDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BidLogSocketService(
    val bidLogRepository: BidLogRepository,
    val playerRepository: PlayerRepository,
    val bidderRepository: BidderRepository,
    val auctionRepository: AuctionRepository
) {

    @Transactional
    // bidder 엔티티의 값을 가져올 때, 연관관계인 auction을 다시 접근하기에 트랜젝션을 통해 1번만 접근하도록 변경
    fun saveBidLog(
        requestBidLog: BidLogSocketDto.Request.BidLogContent
    ): BidLogSocketDto.Response.BidLogContent {

        val auction = auctionRepository.findById(requestBidLog.auctionId)
            .orElseThrow { throw BusinessException(AUCTION_NOT_FOUND) }
        val bidder = bidderRepository.findById(requestBidLog.bidderId)
            .orElseThrow { throw BusinessException(BIDDER_NOT_FOUND) }
        val player = playerRepository.findById(requestBidLog.playerId)
            .orElseThrow { throw BusinessException(PLAYER_NOT_FOUND) }

        val bidLog = BidLog(
            price = requestBidLog.price,
            bidder = bidder,
            auction = auction,
            player = player
        )

        return bidLogRepository.save(bidLog).toDtoWithUpdateTime()
    }
}
