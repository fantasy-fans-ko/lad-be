package com.fantasy.ladbe.socket.controller

import com.fantasy.ladbe.socket.dto.BidLogSocketDto
import com.fantasy.ladbe.socket.service.BidLogSocketService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.RestController

@RestController
class BidLogSocketController(
    val bidLogSocketService: BidLogSocketService
) {
    @MessageMapping("/bidLog/save")
    @SendTo("/topic/bidLog/history")
    fun createBidLog(
        bidLogContent: BidLogSocketDto.Request.BidLogContent
    ): BidLogSocketDto.Response.BidLogContent {
        println(bidLogContent)
        return bidLogSocketService.saveBidLog(bidLogContent)
    }
}
