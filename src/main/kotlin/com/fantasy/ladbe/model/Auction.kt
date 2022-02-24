package com.fantasy.ladbe.model

import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "auctions")
data class Auction(
    @Id @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0L,
    val name: String = "",
    val size: Int = 0,
)
