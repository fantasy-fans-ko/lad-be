package com.fantasy.ladbe.controller

import com.fantasy.ladbe.dto.BidLogDto
import com.fantasy.ladbe.service.BidLogService
import com.fantasy.ladbe.service.BidderService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.RestController

@RestController
class BidLogController(
    val bidLogService: BidLogService
) {
    // 입찰 내역 저장
    @MessageMapping("/log/save") // RequestMapping 과 비슷한 역할
    @SendTo("/topic/log/history") // 핸들러에서 처리를 마친 반환 값을 설정한 경로로 전송
    fun greeting(
        logContents: BidLogDto.Request.LogContents
    ) : String {
        bidLogService.save(logContents)
        return ""
    }
}
