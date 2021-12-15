package com.fantasy.ladbe.handler.exception

enum class Exceptions(val code: String, val message: String?) {
    // Common
    SERVICE_ERROR("COMMON001", "요청 처리에 문제가 있습니다."),
    INVALID_PARAM("COMMON002", "유효하지 않은 요청 데이터입니다."),
    INVALID_URI("COMMON003", "유효하지 않은 URI입니다."),

    // User
    USER_NOT_FOUND("USER001", "존재하지 않은 사용자입니다."),
    PERMISSION_DENIED("USER002", "권한이 없는 사용자입니다."),

    // Jwt
    JWT_WRONG_TYPE_TOKEN("JWT001", "잘못된 JWT의 서명입니다."),
    JWT_EXPIRED_TOKEN("JWT002", "유효가 만료된 JWT 입니다."),
    JWT_UNSUPPORTED_TOKEN("JWT003", "지원하지 않는 JWT 입니다."),
    JWT_WRONG_TOKEN("JWT004", "옳바르지 않은 JWT 입니다."),
    JWT_NULL_TOKEN("JWT005", "토큰을 전달받지 못했습니다."),
    JWT_UNKNOWN_ERROR("JWT006", null), // 이외의 에러가 나왔을 경우, 각 에러에 대한 내용을 추후에 주입하기 위함.
}
