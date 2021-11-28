package com.fantasy.ladbe.controller

import com.fantasy.ladbe.common.web.CommonApiResponse
import com.fantasy.ladbe.dto.UserDto
import com.fantasy.ladbe.service.UserService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/users")
class UserController(
    val userService: UserService
) {
    @GetMapping
    fun getUser(@RequestParam id: Long): CommonApiResponse? {

        return id.let {
            userService.readOne(id)
        }?.let {
            CommonApiResponse.success(it)
        }

    }

    @GetMapping("/all")
    fun getAllUsers(): CommonApiResponse? {
        val userList = userService.readAll()
        return userList?.let { CommonApiResponse.success(it) }
//        return ResponseEntity.ok().body(playerList)
    }

    @PostMapping
    fun createUser(@RequestBody request :UserDto.Request.CreateUser): CommonApiResponse?{
        userService.create(request)
        return CommonApiResponse.success()
    }

}