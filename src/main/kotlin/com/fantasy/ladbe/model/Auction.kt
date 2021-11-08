package com.fantasy.ladbe.model

import javax.persistence.*
import javax.persistence.GenerationType.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "auctions")
data class Auction(
    @Id @GeneratedValue(strategy = IDENTITY)
    val id : Long = 0L,
    @Column(name = "name")
    val name : String = "",
)
