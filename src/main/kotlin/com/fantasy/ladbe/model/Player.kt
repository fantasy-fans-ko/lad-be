package com.fantasy.ladbe.model

import com.fantasy.ladbe.model.enumeration.PlayerStatus
import com.fantasy.ladbe.model.enumeration.PlayerStatus.HEALTHY
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.Column
import javax.persistence.Enumerated
import javax.persistence.EnumType
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "players")
data class Player(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0L,
    @Column(length = 16)
    val name: String = "",
    @Column(length = 4)
    val position: String = "",
    @Column(name = "three_pct")
    val threePct: Float = 0F,
    @Column(name = "ft_pct")
    val ftPct: Float = 0F,
    @Column(name = "fg_pct")
    val fgPct: Float = 0F,
    val points: Int = 0,
    val rebounds: Int = 0,
    val assists: Int = 0,
    val steals: Int = 0,
    val blocks: Int = 0,
    @Column(name = "turn_overs")
    val turnOvers: Int = 0,
    @Column(name = "triple_doubles")
    val tripleDoubles: Int = 0,
    @Column(name = "team_name")
    val teamName: String = "",
    @Enumerated(EnumType.STRING)
    val status: PlayerStatus = HEALTHY,
    @Column(name = "image_url")
    val imageUrl: String = "",
    @Column(name = "rank_pre")
    val rankPre: Int = 0,
    @Column(name = "rank_current")
    val rankCurrent: Int = 0,
)
