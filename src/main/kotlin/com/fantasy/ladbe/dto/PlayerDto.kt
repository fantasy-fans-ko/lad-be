package com.fantasy.ladbe.dto

import com.fantasy.ladbe.common.dto.PageParam
import com.fantasy.ladbe.model.Player
import com.fantasy.ladbe.model.enumeration.PlayerStatus

class PlayerDto {
    class Request {
        data class PlayerPage(
            val pageable: PageParam
        )
    }

    class Response {
        data class PlayerDetail(
            val id: Long = 0L,
            val name: String = "",
            val position: String = "",
            val threePct: Float = 0F,
            val ftPct: Float = 0F,
            val fgPct: Float = 0F,
            val points: Int = 0,
            val rebounds: Int = 0,
            val assists: Int = 0,
            val steals: Int = 0,
            val blocks: Int = 0,
            val turnOvers: Int = 0,
            val tripleDoubles: Int = 0,
            val teamName: String = "",
            val status: PlayerStatus = PlayerStatus.HEALTHY,
            val imageUrl: String = "",
            val rankPre: Int = 0,
            val rankCurrent: Int = 0,
        ) {
            fun entityToDto(player: Player): PlayerDetail {
                return PlayerDetail(
                    id = player.id,
                    name = player.name,
                    position = player.position,
                    threePct = player.threePct,
                    ftPct = player.ftPct,
                    fgPct = player.fgPct,
                    points = player.points,
                    rebounds = player.rebounds,
                    assists = player.assists,
                    steals = player.steals,
                    blocks = player.blocks,
                    turnOvers = player.turnOvers,
                    tripleDoubles = player.tripleDoubles,
                    teamName = player.teamName,
                    status = player.status,
                    imageUrl = player.imageUrl,
                    rankPre = player.rankPre,
                    rankCurrent = player.rankCurrent
                )
            }
        }
    }
}
