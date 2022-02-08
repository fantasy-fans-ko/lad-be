package com.fantasy.ladbe.controller

import com.fantasy.ladbe.common.web.CommonApiResponse
import com.fantasy.ladbe.dto.UploadDto
import com.fantasy.ladbe.service.UploadService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("api/upload")
class UploadController(
    val uploadService: UploadService,
) {
    @PostMapping
    fun upload(@Valid @RequestBody request: UploadDto.Request.UploadS3Path) =
        ResponseEntity(CommonApiResponse.success(uploadService.uploadDirectory(request.s3Path)),
            HttpStatus.OK)
}

