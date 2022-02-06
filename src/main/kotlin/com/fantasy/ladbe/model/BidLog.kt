package com.fantasy.ladbe.model

import javax.persistence.*
import javax.persistence.FetchType.LAZY
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "bid_logs")
data class BidLog(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0L,
    @Column(name = "price")
    val price: Int = 0,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "bidder_id")
    val bidder: Bidder,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "player_id")
    val player: Player,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "auction_id")
    val auction: Auction,
)
