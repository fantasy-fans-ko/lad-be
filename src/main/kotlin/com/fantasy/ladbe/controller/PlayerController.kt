package com.fantasy.ladbe.controller

import com.fantasy.ladbe.common.dto.PageDto
import com.fantasy.ladbe.common.web.CommonApiResponse
import com.fantasy.ladbe.common.web.CommonApiResponse.Companion.success
import com.fantasy.ladbe.dto.PlayerDto
import com.fantasy.ladbe.service.PlayerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/players")
class PlayerController(
    val playerService: PlayerService,
) {

    @GetMapping
    fun getPlayer(@RequestParam id: Long): ResponseEntity<CommonApiResponse> {
        val body = playerService.readOne(id)?.let { success(it) }
        return ResponseEntity(body, HttpStatus.OK)
    }

    @GetMapping("/all")
    fun getAllPlayers(): ResponseEntity<CommonApiResponse> {
        val body = playerService.readAll()?.let { success(it) }
        return ResponseEntity(body, HttpStatus.OK)
    }

    @GetMapping("/page")
    fun getPlayersByPaging(@Valid @RequestBody request: PlayerDto.Request.PlayerPage): ResponseEntity<CommonApiResponse> {
        val body = playerService.readPage(request)?.let {
            val pageDto = PageDto(page = it, content = it.content)
            success(pageDto)
        }
        return ResponseEntity(body, HttpStatus.OK)

    }
}