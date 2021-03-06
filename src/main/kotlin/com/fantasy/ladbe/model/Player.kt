package com.fantasy.ladbe.model

import com.fantasy.ladbe.dto.PlayerDto
import com.fantasy.ladbe.model.enumeration.PlayerStatus
import com.fantasy.ladbe.model.enumeration.PlayerStatus.HEALTHY
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.Table

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
    @Column(name = "image_path")
    val imagePath: String = "",
    @Column(name = "rank_pre")
    val rankPre: Int = 0,
    @Column(name = "rank_current")
    val rankCurrent: Int = 0,
) {
    fun toDto(): PlayerDto.Response.PlayerDetail {
        return PlayerDto.Response.PlayerDetail(
            id = id,
            name = name,
            position = position,
            threePct = threePct,
            ftPct = ftPct,
            fgPct = fgPct,
            points = points,
            rebounds = rebounds,
            assists = assists,
            steals = steals,
            blocks = blocks,
            turnOvers = turnOvers,
            tripleDoubles = tripleDoubles,
            teamName = teamName,
            status = status,
            imagePath = imagePath,
            rankPre = rankPre,
            rankCurrent = rankCurrent
        )
    }
}
