package com.fantasy.ladbe.common.web

/**
 * resultCode : 에러 코드
 * httpStatus : 에러 상태에 대한 정보
 * httpMethod : 사용된 method
 * message : 에러에 대한 내용
 * path : 에러가 발생된 경로
 * timestamp : 에러가 발생된 시간
 */
data class ErrorResponse (
    val resultCode: String = "",
    val httpStatus: String = "",
    val httpMethod: String = "",
    val message: String = "",
    val path: String = "",
    val timestamp: Long = 0L
) {

}