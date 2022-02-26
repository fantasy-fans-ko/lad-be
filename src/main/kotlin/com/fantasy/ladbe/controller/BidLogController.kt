package com.fantasy.ladbe.controller

import com.fantasy.ladbe.service.BidLogService
import org.springframework.web.bind.annotation.RestController

@RestController
class BidLogController(
    val bidLogService: BidLogService
)
