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
//    val imageUrl : String = "",
//    TODO : 각 옥션마다 프사를 변경했을 때, 저장 (기본값 : 카카오 프사)
    val budget: Int = 0,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "user_id")
    val user: User,
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "auction_id")
    val auction: Auction,
)
