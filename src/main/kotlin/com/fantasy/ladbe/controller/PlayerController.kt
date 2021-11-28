package com.fantasy.ladbe.controller

import com.fantasy.ladbe.common.dto.PageDto
import com.fantasy.ladbe.common.web.CommonApiResponse
import com.fantasy.ladbe.common.web.CommonApiResponse.Companion.success
import com.fantasy.ladbe.dto.PlayerDto
import com.fantasy.ladbe.service.PlayerService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/players")
class PlayerController(
    val playerService: PlayerService,
) {

    @GetMapping
    fun getPlayer(@RequestParam(required = true) id: Long?): CommonApiResponse? {

        return id?.let {
            playerService.readOne(id)
        }?.let {
            success(it)
        }

    }

    @GetMapping("/all")
    fun getAllPlayers(): CommonApiResponse? {
        val playerList = playerService.readAll()
        return playerList?.let { success(it) }
    }

    @GetMapping("/page")
    fun getPlayersByPaging(@Valid @RequestBody request: PlayerDto.Request.PlayerPage): CommonApiResponse? {
        val result = playerService.readPage(request)
        val pageDto = PageDto(page = result, content = result.content)
        return success(pageDto)

    }
}