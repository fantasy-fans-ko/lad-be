package com.fantasy.ladbe.repository.custom.impl

import com.fantasy.ladbe.dto.BidderDto
import com.fantasy.ladbe.dto.QBidderDto_Response_BidderDetail
import com.fantasy.ladbe.model.Bidder
import com.fantasy.ladbe.model.QAuction.auction
import com.fantasy.ladbe.model.QBidder.bidder
import com.fantasy.ladbe.repository.custom.BidderCustomRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import javax.annotation.Resource

@Repository
class BidderCustomRepositoryImpl(
    @Autowired @Resource(name = "jpaQueryFactory") val queryFactory: JPAQueryFactory,
) : QuerydslRepositorySupport(Bidder::class.java), BidderCustomRepository {

    override fun selectBiddersByAuctionId(auctionId: Long): List<BidderDto.Response.BidderDetail>? {
        return queryFactory.select(
            QBidderDto_Response_BidderDetail(
                bidder.id,
                bidder.nickname,
                bidder.imagePath,
                bidder.budget
            )
        ).from(bidder)
            .innerJoin(bidder.auction, auction)
            .where(auction.id.eq(auctionId))
            .fetch()
    }
}
