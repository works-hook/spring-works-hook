package spring.works.hook.util.error

class MyException(
    val errorCode: ErrorCode
) : RuntimeException(errorCode.message)
