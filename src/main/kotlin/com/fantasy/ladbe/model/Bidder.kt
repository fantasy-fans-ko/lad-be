package com.fantasy.ladbe.model

import javax.persistence.*
import javax.persistence.FetchType.*
import javax.persistence.GenerationType.*

@Entity
@Table(name = "bidders")
data class Bidder(
    @Id @GeneratedValue(strategy = IDENTITY)
    val id : Long = 0L,
    val nickname : String = "",
//    val imageUrl : String = "",
    val budget : Int = 0,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "user_id")
    val user : User,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "auction_id")
    val auction : Auction,
)
