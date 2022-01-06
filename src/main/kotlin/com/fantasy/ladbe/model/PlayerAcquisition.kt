package com.fantasy.ladbe.model

import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "player_acquisitions")
data class PlayerAcquisition(
    @Id @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0L,
    @Column(name = "pick_number")
    val pickNumber: Int = 0,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "action_id")
    val auction: Auction,
)
