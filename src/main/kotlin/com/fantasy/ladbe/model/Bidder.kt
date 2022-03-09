package com.fantasy.ladbe.model

import com.fantasy.ladbe.dto.BidderDto
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType.LAZY
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "bidders")
data class Bidder(
    @Id @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0L,
    val nickname: String = "",
    @Column(name = "image_path")
    val imagePath: String = "",
    val budget: Int = 0,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "auction_id")
    val auction: Auction,
    @OneToOne(fetch = LAZY) @JoinColumn(name = "user_id")
    val user: User,
) {
    fun toDto(): BidderDto.Response.BidderDetail {
        return BidderDto.Response.BidderDetail(
            id = id,
            nickname = nickname,
            imagePath = imagePath,
            budget = budget,
        )
    }
}
