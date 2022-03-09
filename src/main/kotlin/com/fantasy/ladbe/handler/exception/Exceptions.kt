package com.fantasy.ladbe.handler.exception

import org.springframework.http.HttpStatus

/**
 * @param status Http의 상태 정보
 * @param code 에러 코드
 * @param message 에러 메시지
 */
enum class Exceptions(
    val status: HttpStatus,
    val code: String,
    val message: String
) {
    // Common
    SERVICE_ERROR(HttpStatus.BAD_REQUEST, "COMMON001", "요청 처리에 문제가 있습니다."),
    INVALID_PARAM(HttpStatus.BAD_REQUEST, "COMMON002", "유효하지 않은 요청 데이터입니다."),
    INVALID_URI(HttpStatus.BAD_REQUEST, "COMMON003", "유효하지 않은 URI입니다."),
    OAUTH2_REQUEST(HttpStatus.NOT_FOUND, "COMMON004", "OAuth의 요청이 존재하지 않습니다."),
    OAUTH2_REDIRECT_URL(HttpStatus.NOT_FOUND, "COMMON005", "Redirect 가 지정되어 있지 않습니다."),
    RESPONSE_NOT_COMMITTED(HttpStatus.BAD_REQUEST, "COMMON006", "응답이 커밋되지 않은 상태입니다."),

    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER001", "존재하지 않은 사용자입니다."),
    PERMISSION_DENIED(HttpStatus.UNAUTHORIZED, "USER002", "권한이 없는 사용자입니다."),
    USER_NOT_LOGIN(HttpStatus.NOT_FOUND, "USER003", "로그인이 되어있지 않는 사용자입니다."),

    // Jwt
    JWT_WRONG_TYPE_TOKEN(HttpStatus.BAD_REQUEST, "JWT001", "잘못된 JWT의 서명입니다."),
    JWT_EXPIRED_TOKEN(HttpStatus.BAD_REQUEST, "JWT002", "유효가 만료된 JWT 입니다."),
    JWT_UNSUPPORTED_TOKEN(HttpStatus.BAD_REQUEST, "JWT003", "지원하지 않는 JWT 입니다."),
    JWT_WRONG_TOKEN(HttpStatus.BAD_REQUEST, "JWT004", "옳바르지 않은 JWT 입니다."),
    JWT_NULL_TOKEN(HttpStatus.NOT_FOUND, "JWT005", "토큰을 전달받지 못했습니다."),
    JWT_UNKNOWN_ERROR(HttpStatus.BAD_REQUEST, "JWT006", "JWT 유효성 검사에 실패했습니다."),

    // Player
    PLAYER_NOT_FOUND(HttpStatus.NOT_FOUND, "PLAYER001", "존재하지 않는 선수입니다."),

    // Auction
    AUCTION_NOT_FOUND(HttpStatus.NOT_FOUND, "AUCTION001", "존재하지 않는 옥션 방입니다."),

    // Bidder
    BIDDER_NOT_FOUND(HttpStatus.NOT_FOUND, "BIDDER001", "존재하지 않는 유저입니다."),
}
