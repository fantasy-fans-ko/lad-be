package com.fantasy.ladbe.controller

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class BiddingController {
    @MessageMapping("/hello") // = RequestMapping 과 비슷한 역할
    @SendTo("/topic/greeting") // 핸들러에서 처리를 마친 반환 값을 설정한 경로로 전송
    fun greeting(

    ) {

    }
}
