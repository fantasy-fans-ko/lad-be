package com.fantasy.ladbe.dto

import com.fantasy.ladbe.common.dto.PageParam
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
            val imagePath: String = "",
            val rankPre: Int = 0,
            val rankCurrent: Int = 0,
        )
    }
}
