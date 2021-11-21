package com.fantasy.ladbe.dto

import com.fantasy.ladbe.common.dto.PageParam
import com.fantasy.ladbe.model.Player
import com.fantasy.ladbe.model.enumeration.PlayerStatus
import com.querydsl.core.annotations.QueryProjection


class PlayerDto {
    class Request {
        data class PlayerPage(
            val pageable: PageParam,
            ) {

        }

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
                return PlayerDetail().apply {
                    this.id = player.id
                    this.name = player.name
                    this.position = player.position
                    this.threePct = player.threePct
                    this.ftPct = player.ftPct
                    this.fgPct = player.fgPct
                    this.points = player.points
                    this.rebounds = player.rebounds
                    this.assists = player.assists
                    this.steals = player.steals
                    this.blocks = player.blocks
                    this.turnOvers = player.turnOvers
                    this.tripleDoubles = player.tripleDoubles
                    this.teamName = player.teamName
                    this.status = player.status
                    this.imageUrl = player.imageUrl
                    this.rankPre = player.rankPre
                    this.rankCurrent = player.rankCurrent
                }

            }

        }


    }


}