package com.fantasy.ladbe.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "auctions")
data class Auction(
    @Id @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0L,
    val name: String = "",
    val size: Int = 0,
)
