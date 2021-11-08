package com.fantasy.ladbe.model

import javax.persistence.*
import javax.persistence.GenerationType.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "auctions")
data class Auction(
    @Id @GeneratedValue(strategy = IDENTITY)
    var id : Long = 0L,
    @OneToMany(mappedBy = "auction")
    var playerAcquisitions: List<PlayerAcquisition> = mutableListOf(),
    @Column(name = "auction_name")
    var auctionName : String = "",

)
