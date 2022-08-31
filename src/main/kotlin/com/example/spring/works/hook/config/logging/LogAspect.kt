package com.example.spring.works.hook.config.logging

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.Arrays

@Aspect
@Component
class LogAspect {

    private val logger = KotlinLogging.logger {}

    @Around("bean(*Controller)")
    fun controllerLogging(joinPoint: ProceedingJoinPoint): Any {
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        val response = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).response

        var result: Any? = null
        val start = System.currentTimeMillis()
        return try {
            result = joinPoint.proceed(joinPoint.args)
            result
        } finally {
            val end = System.currentTimeMillis()
            logger.info("---------> Request: {} {} {}", request.method, request.requestURI, Arrays.toString(joinPoint.args))
            logger.info("---------> Response: {} {} ({}ms)", response?.status, result, end - start)
        }
    }
}
