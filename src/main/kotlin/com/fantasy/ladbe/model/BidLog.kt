package com.fantasy.ladbe.model

import javax.persistence.*
import javax.persistence.FetchType.*
import javax.persistence.GenerationType.*

@Entity
@Table(name = "bid_logs")
data class BidLog(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id : Long = 0L,
    @Column(name = "price")
    val price : Int = 0,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "user_id")
    val user : User,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "player_id")
    val player : Player,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "player_acquisition_id")
    val playerAcquisition: PlayerAcquisition? = null,
)
