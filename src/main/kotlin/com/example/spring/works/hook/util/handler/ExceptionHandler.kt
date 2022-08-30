package com.example.spring.works.hook.util.handler

import com.example.spring.works.hook.util.ApiResponse.Error
import com.example.spring.works.hook.util.error.MyException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

// @ControllerAdvice + @ResponseBody
// controller 에 대한 전역으로 발생하는 예외를 잡아서 처리할 수 있음 (AOP)
@RestControllerAdvice
class ExceptionHandler() {

    @ExceptionHandler(MyException::class)
    fun handleMemberException(e: MyException): Error<HttpStatus> {
        return Error(e.errorCode.status, e.message)
    }
}
