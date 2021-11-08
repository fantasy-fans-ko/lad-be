package com.fantasy.ladbe.model

import javax.persistence.*
import javax.persistence.GenerationType.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "player_acquisitions")
data class PlayerAcquisition(
    @Id @GeneratedValue(strategy = IDENTITY)
    val id : Long? = 0L,
    @NotNull @Column(name = "selected_num")
    val selectedNum : Int = 0,
    @NotNull @Column(name = "user_budget")
    val userBudget : Int = 0,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "action_id")
    val auction : Auction? = null,
)
