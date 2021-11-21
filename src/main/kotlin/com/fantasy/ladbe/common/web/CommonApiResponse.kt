package com.fantasy.ladbe.common.web

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import java.time.LocalDateTime
import java.util.stream.Collectors


@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class CommonApiResponse(
    val code: String,
    val message: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val data: Any? = null,
) {


    companion object {
        private const val OK_CODE = "OK001"
        private const val OK_MESSAGE = "OK"

        fun success(): CommonApiResponse {
            return CommonApiResponse(code = OK_CODE, message = OK_MESSAGE)
        }

        fun success(any: Any): CommonApiResponse {
            return CommonApiResponse(code = OK_CODE, message = OK_MESSAGE, data = any)
        }


    }

}