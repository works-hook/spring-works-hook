package com.example.spring.works.hook.util.error

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val message: String
) {
    INTERNAL_SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "시스템에서 문제가 발생하였습니다."),

    EXPIRED_TOKEN_ERROR(HttpStatus.NOT_ACCEPTABLE, "유효하지 않은 토큰입니다."),
    NO_TOKEN_ERROR(HttpStatus.NOT_ACCEPTABLE, "존재하지 않은 토큰입니다."),
    INVALID_TOKEN_ERROR(HttpStatus.NOT_ACCEPTABLE, "토큰 에러입니다. 관리자에게 문의해주세요."),

    EXIST_NICK_NAME(HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다."),
    NOT_EXIST_MEMBER(HttpStatus.BAD_REQUEST, "존재하지 않은 사용자입니다."),

    EXIST_AUTHOR(HttpStatus.BAD_REQUEST, "이미 존재하는 작가입니다."),
    NOT_EXIST_AUTHOR(HttpStatus.BAD_REQUEST, "존재하지 않은 작가입니다."),

    EXIST_VISITOR(HttpStatus.BAD_REQUEST, "이미 방문자로 등록되어 있는 사용자입니다."),
    NOT_EXIST_VISITOR(HttpStatus.BAD_REQUEST, "존재하지 않은 방문 기록입니다.")
}
