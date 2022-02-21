package com.fantasy.ladbe.model

import javax.persistence.*
import javax.persistence.FetchType.LAZY
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "bidders")
data class Bidder(
    @Id @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0L,
    val nickname: String = "",
    @Column(name = "image_path")
    val imageUrl : String = "",
    val budget: Int = 0,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "auction_id")
    val auction: Auction,
    @OneToOne(fetch = LAZY) @JoinColumn(name = "user_id")
    val user: User,
)
