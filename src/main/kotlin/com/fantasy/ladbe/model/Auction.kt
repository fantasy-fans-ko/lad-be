package com.fantasy.ladbe.model

import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "auctions")
data class Auction(
    @Id @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0L,
    @Column(name = "name", length = 36)
    val name: String? = "",
)
