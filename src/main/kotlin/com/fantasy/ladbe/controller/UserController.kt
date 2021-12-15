package com.fantasy.ladbe.controller

import com.fantasy.ladbe.common.web.CommonApiResponse
import com.fantasy.ladbe.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/users")
class UserController(
    val userService: UserService
) {
    @GetMapping
    fun getPlayer(@RequestParam id: Long): ResponseEntity<CommonApiResponse> {
        val body = userService.readOne(id)?.let { CommonApiResponse.success(it) }
        return ResponseEntity(body, HttpStatus.OK)
    }

    @GetMapping("/all")
    fun getAllPlayers(): ResponseEntity<CommonApiResponse> {
        val body = userService.readAll()?.let { CommonApiResponse.success(it) }
        return ResponseEntity(body, HttpStatus.OK)
    }
}
