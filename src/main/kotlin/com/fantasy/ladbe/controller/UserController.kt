package com.fantasy.ladbe.controller

import com.fantasy.ladbe.common.web.CommonApiResponse
import com.fantasy.ladbe.common.web.CommonApiResponse.Companion.success
import com.fantasy.ladbe.service.UserService
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("api/users")
class UserController(
    val userService: UserService,
) {
    @GetMapping
    fun getUserById(@RequestParam @Valid id: Long): ResponseEntity<CommonApiResponse> =
        ResponseEntity(success(userService.readOneById(id)), OK)

    @GetMapping("/auth")
    fun getUserAfterLogin(): ResponseEntity<CommonApiResponse> =
        ResponseEntity(success(userService.readOneByContext()), OK)

    @GetMapping("/all")
    fun getAllUsers(): ResponseEntity<CommonApiResponse> =
        ResponseEntity(success(userService.readAll()), OK)
}
