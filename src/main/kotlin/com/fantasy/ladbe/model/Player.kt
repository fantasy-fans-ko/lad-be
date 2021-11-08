package com.fantasy.ladbe.model

import com.fantasy.ladbe.model.enumeration.PlayerStatus
import javax.persistence.*
import javax.persistence.GenerationType.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "players")
data class Player(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id : Long = 0L,
    @NotNull @Column(name = "name")
    val name : String = "",
    @NotNull @Column(name = "position")
    val position : String = "",
    @NotNull @Column(name = "three_pct")
    val three_pct : Float = 0F,
    @NotNull @Column(name = "ft_pct")
    val tf_pct : Float = 0F,
    @NotNull @Column(name = "fg_pct")
    val fg_pct : Float = 0F,
    @NotNull @Column(name = "pts")
    val pts : Int = 0,
    @NotNull @Column(name = "reb")
    val reb : Int = 0,
    @NotNull @Column(name = "ast")
    val ast : Int = 0,
    @NotNull @Column(name = "st")
    val st : Int = 0,
    @NotNull @Column(name = "blk")
    val blk : Int = 0,
    @NotNull @Column(name = "to")
    val to : Int = 0,
    @NotNull @Column(name = "td")
    val td : Int = 0,
    @NotNull @Column(name = "team")
    val team : String = "",
    @NotNull @Column(name = "status") @Enumerated(EnumType.STRING)
    val status : PlayerStatus = PlayerStatus.HEALTHY,
    )
