package com.example.spring.works.hook.schedule.caller

import com.example.spring.works.hook.schedule.dto.SlackTimeRequestDto
import com.example.spring.works.hook.util.error.ErrorCode
import com.example.spring.works.hook.util.error.MyException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class LocalTimeApiCallerImpl() : LocalTimeApiCaller {

    @Autowired
    private lateinit var webClient: WebClient

    override fun testPrintNowDate() {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

        webClient.post()
            .accept(MediaType.ALL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(SlackTimeRequestDto("현재 시간은 ${ LocalDateTime.now().format(formatter) } 입니다."))
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError) { Mono.error(MyException(ErrorCode.CLIENT_API_CALLER)) }
            .onStatus(HttpStatus::is5xxServerError) { Mono.error(MyException(ErrorCode.SERVER_API_CALLER)) }
            .bodyToMono<String>()
            .block()
    }
}
