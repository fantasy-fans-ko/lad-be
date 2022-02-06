package com.fantasy.ladbe.dto

class BidLogDto {
    class Request {
        data class LogContents(
            val playerId: Long = 0L, // 선수 id
            val price: Int = 0, // 입찰 금액
            val bidderId: Long = 0L, // 입찰자 id
            val auctionId: Long = 0L, // 옥션 방 id
        ) {

        }
    }

    class Response {
        data class BidLogDetail(
            val id: Long = 0L,
            val price: Int = 0,
            val bidderId: Long = 0L,
            )

        data class SelectedUser(
            val bidderId: Long = 0L,
            val auctionId: Long = 0L,
            val price: Int = 0,
            val playerName: String = "",
            val time: Int = 10,
        ) {

        }
    }
}
