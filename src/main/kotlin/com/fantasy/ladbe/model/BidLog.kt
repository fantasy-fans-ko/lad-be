package com.fantasy.ladbe.model

import javax.persistence.*
import javax.persistence.FetchType.*
import javax.persistence.GenerationType.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "bid_logs")
data class BidLog(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id : Long = 0L,
    @NotNull @Column(name = "price")
    val price : Int = 0,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "user_id", nullable = true)
    val user : User? = null,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "player_id", nullable = true)
    val player : Player? = null,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "player_ac_id", nullable = true)
    val playerAcquisition: PlayerAcquisition? = null,
)
