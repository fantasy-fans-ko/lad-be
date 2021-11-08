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
    var id : Long = 0L,
    @NotNull @Column(name = "bid_amount")
    var bidAmount : Int = 0,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "users_id", nullable = true)
    var user : User? = null,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "players_id", nullable = true)
    var player : Player? = null,
    @OneToOne(mappedBy = "bidLog")
    var acquisition : PlayerAcquisition? = null,

)
