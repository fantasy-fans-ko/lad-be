package com.fantasy.ladbe.model

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
@Table(name = "player_acquisitions")
data class PlayerAcquisition(
    @Id @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0L,
    @Column(name = "pick_number")
    val pickNumber: Int = 0,
    @Column(name = "price")
    val price: Int = 0,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "action_id")
    val auction: Auction,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "bidder_id")
    val bidder: Bidder,
    @OneToOne(fetch = LAZY) @JoinColumn(name = "bid_log_id")
    val bidLog: BidLog,
)
