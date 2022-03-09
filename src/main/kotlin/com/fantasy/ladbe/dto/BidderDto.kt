package com.fantasy.ladbe.dto

import com.querydsl.core.annotations.QueryProjection

class BidderDto {
    class Response {
        data class BidderDetail @QueryProjection constructor(
            val id: Long,
            val nickname: String,
            val imagePath: String,
            val budget: Int,
        )
    }
}
