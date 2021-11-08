package com.fantasy.ladbe.model

import javax.persistence.*
import javax.persistence.GenerationType.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "player_acquisitions")
data class PlayerAcquisition(
    @Id @GeneratedValue(strategy = IDENTITY)
    var id : Long? = 0L,
    @NotNull @Column(name = "acquisition_amount")
    var acquisitionAmount : Int = 0,
    @NotNull @Column(name = "selected_num")
    var selectedNum : Int = 0,
    @NotNull @Column(name = "user_budget")
    var userBudget : Int = 0,
    @OneToOne
    var bidLog: BidLog? = null,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "actions_id")
    var auction : Auction? = null,
)
