package com.fantasy.ladbe.common.web

import java.time.Instant

data class CommonApiResponse(
    val code: String,
    val message: String,
    val timestamp: Long = Instant.now().epochSecond,
    val data: Any? = null,
) {

    companion object {
        private const val OK_CODE = "OK001"
        private const val OK_MESSAGE = "OK"

        fun success(): CommonApiResponse =
            CommonApiResponse(code = OK_CODE, message = OK_MESSAGE)

        fun success(any: Any): CommonApiResponse =
            CommonApiResponse(code = OK_CODE, message = OK_MESSAGE, data = any)
    }
}
