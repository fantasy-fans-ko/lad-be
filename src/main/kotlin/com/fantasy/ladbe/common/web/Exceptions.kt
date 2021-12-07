package com.fantasy.ladbe.common.web

enum class Exceptions(val code: String, val message: String) {
    //Common
    SERVICE_ERROR("COMMON001", "요청 처리에 문제가 있습니다."),
    INVALID_PARAM("COMMON002", "유효하지 않은 요청 데이터입니다."),
    INVALID_URI("COMMON003", "유효하지 않은 URI입니다.")
}