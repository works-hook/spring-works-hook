package com.example.spring.works.hook.util.error

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val message: String
) {
    INTERNAL_SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "시스템에서 문제가 발생하였습니다."),

    CLIENT_API_CALLER(HttpStatus.BAD_REQUEST, "잘못된 API 요청입니다."),
    SERVER_API_CALLER(HttpStatus.INTERNAL_SERVER_ERROR, "API 통신 중 에러가 발생하였습니다.")
}
