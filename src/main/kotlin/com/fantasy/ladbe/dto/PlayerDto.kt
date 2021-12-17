package com.fantasy.ladbe.dto

import com.fantasy.ladbe.common.dto.PageParam
import com.fantasy.ladbe.model.Player
import com.fantasy.ladbe.model.enumeration.PlayerStatus
import com.querydsl.core.annotations.QueryProjection


class PlayerDto {
    class Request {
        data class PlayerPage(val pageable: PageParam)

    }

    class Response {

        data class PlayerDetail @QueryProjection constructor(
            var id: Long? = null,
            var name: String? = null,
            var position: String? = null,
            var threePct: Float? = null,
            var ftPct: Float? = null,
            var fgPct: Float? = null,
            var points: Int? = null,
            var rebounds: Int? = null,
            var assists: Int? = null,
            var steals: Int? = null,
            var blocks: Int? = null,
            var turnOvers: Int? = null,
            var tripleDoubles: Int? = null,
            var teamName: String? = null,
            var status: PlayerStatus? = null,
            var imageUrl: String? = null,
            var rankPre: Int? = null,
            var rankCurrent: Int? = null,
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

