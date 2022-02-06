package com.fantasy.ladbe.controller

import com.fantasy.ladbe.common.web.CommonApiResponse
import com.fantasy.ladbe.common.web.CommonApiResponse.Companion.success
import com.fantasy.ladbe.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/users")
class UserController(
    val userService: UserService
) {
    @GetMapping
    fun getPlayer(@RequestParam id: Long): ResponseEntity<CommonApiResponse> =
        ResponseEntity(success(userService.readOne(id)), HttpStatus.OK)

    @GetMapping("/all")
    fun getAllPlayers(): ResponseEntity<CommonApiResponse> =
        ResponseEntity(success(userService.readAll()), HttpStatus.OK)

}
