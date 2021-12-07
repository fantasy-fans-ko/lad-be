package com.fantasy.ladbe.common.web

import java.time.LocalDateTime

data class ErrorResponse(
    var resultCode: String? = null,
    var httpStatus: String? = null,
    var httpMethod: String? = null,
    var message: String? = null,
    var path: String? = null,
    var timestamp: LocalDateTime? = null,
    var errors: MutableList<Error>? = null,
    )

data class Error(
    var field: String? = null,
    var message: String? = null,
    var value: Any? = null,
)