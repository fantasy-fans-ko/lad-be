package com.fantasy.ladbe.model

import com.fantasy.ladbe.model.enumeration.PlayerStatus
import com.fantasy.ladbe.model.enumeration.PlayerStatus.*
import javax.persistence.*
import javax.persistence.GenerationType.*

@Entity
@Table(name = "players")
data class Player(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id : Long = 0L,
    @Column(length = 16)
    val name : String = "",
    @Column(length = 4)
    val position : String = "",
    @Column(name = "three_pct")
    val threePct : Float = 0F,
    @Column(name = "ft_pct")
    val tfPct : Float = 0F,
    @Column(name = "fg_pct")
    val fgPct : Float = 0F,
    val points : Int = 0,
    val rebound : Int = 0,
    val assist : Int = 0,
    val steal : Int = 0,
    val block : Int = 0,
    @Column(name = "turn_over")
    val turnOver : Int = 0,
    @Column(name = "triple_double")
    val tripleDouble : Int = 0,
    @Column(name = "team_name")
    val teamName : String = "",
    @Enumerated(EnumType.STRING)
    val status : PlayerStatus = HEALTHY,
    )
