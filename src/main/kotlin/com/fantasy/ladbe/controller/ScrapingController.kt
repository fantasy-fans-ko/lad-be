package com.fantasy.ladbe.controller

import com.fantasy.ladbe.service.ScrapingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/scraping")
class ScrapingController {

    @Autowired
    private lateinit var scrapingService : ScrapingService

    @GetMapping("/players")
    fun scrapingFromHtml() : ResponseEntity<Void> {
        scrapingService.iterativeApproachToHtml()

        return ResponseEntity<Void>(OK)
    }
}
g
