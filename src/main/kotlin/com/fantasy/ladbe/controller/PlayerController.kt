package com.fantasy.ladbe.controller

import com.fantasy.ladbe.common.dto.PageDto
import com.fantasy.ladbe.common.dto.PageParam
import com.fantasy.ladbe.common.web.CommonApiResponse
import com.fantasy.ladbe.common.web.CommonApiResponse.Companion.success
import com.fantasy.ladbe.dto.PlayerDto
import com.fantasy.ladbe.service.PlayerService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.data.domain.Sort.Direction
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("api/players")
class PlayerController(
    val playerService: PlayerService,
) {

    @GetMapping
    fun getPlayer(@RequestParam @Valid id: Long) = ResponseEntity(success(playerService.readOne(id)), HttpStatus.OK)

    @GetMapping("/all")
    fun getAllPlayers() = ResponseEntity(success(playerService.readAll()), HttpStatus.OK)

    @GetMapping("/page")
    fun getPlayersByPaging(
        @RequestParam param: Map<Any, Any>
    ): ResponseEntity<CommonApiResponse> {
        val sort = jacksonObjectMapper().readValue(param["sort"].toString(), Map::class.java) as Map<String, Direction>?
        val request = PlayerDto.Request.PlayerPage(
            PageParam(
                Integer.parseInt(param["page"].toString()), param["size"]?.let { Integer.parseInt(it.toString()) }?: null, sort
            )
        )
        val readPage = playerService.readPage(request)
        val pageDto = PageDto(page = readPage, content = readPage.content)
        return ResponseEntity(success(pageDto), HttpStatus.OK)
    }

//    @GetMapping("/position")
//    fun getPlayersByPosition(
//        @RequestParam(required = true, name = "position") position: String
//    ): ResponseEntity<CommonApiResponse> {
//
//    }
}
