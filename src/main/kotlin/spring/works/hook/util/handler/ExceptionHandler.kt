package spring.works.hook.util.handler

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import spring.works.hook.util.ApiResponse.Error
import spring.works.hook.util.error.MyException

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(MyException::class)
    fun handleMemberException(e: MyException): Error<HttpStatus> {
        return Error(e.errorCode.status, e.message)
    }
}
