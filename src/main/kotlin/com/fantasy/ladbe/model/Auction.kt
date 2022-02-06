package com.fantasy.ladbe.model

import javax.persistence.*
import javax.persistence.FetchType.LAZY
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "auctions")
data class Auction(
    @Id @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0L,
    @Column(name = "name")
    val name: String? = "",
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "user_id")
    val user: User
) {

}
