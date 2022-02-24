package com.fantasy.ladbe.socket.dto

class BidLogSocketDto {
    class Request {
        data class BidLogContent(
            val playerId: Long = 0L,
            val bidderId: Long = 0L,
            val price: Int = 0,
            val auctionId: Long = 0L,
        )

    }
    class Response {
        data class BidLogContent(
            val price: Int = 0,
            val time: Int = 10,
            val playerId: Long = 0L,
            val bidderId: Long = 0L,
        )
    }
}
