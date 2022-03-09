package com.fantasy.ladbe.controller

import com.fantasy.ladbe.common.web.CommonApiResponse
import com.fantasy.ladbe.common.web.CommonApiResponse.Companion.success
import com.fantasy.ladbe.service.BidderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/bidders")
@RestController
class BidderController(
    val bidderService: BidderService
) {
    @GetMapping("/all")
    fun getAllBiddersByAuctionId(
        @RequestParam auctionId: Long
    ): ResponseEntity<CommonApiResponse> {
        return ResponseEntity(success(bidderService.readAllByAuctionId(auctionId)), HttpStatus.OK)
    }
}
