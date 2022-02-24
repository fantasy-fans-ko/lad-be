package com.fantasy.ladbe.dto

class AuctionDto {
    class Response {
        data class AuctionDetails (
            val id : Long = 0L,
            val name: String? = "",
            ) {

        }
    }
}
