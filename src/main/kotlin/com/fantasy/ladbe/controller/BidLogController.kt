package com.fantasy.ladbe.controller

import com.fantasy.ladbe.service.BidLogService
import com.fantasy.ladbe.service.BidderService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.RestController

@RestController
class BidLogController(
    val bidLogService: BidLogService
) {

}
