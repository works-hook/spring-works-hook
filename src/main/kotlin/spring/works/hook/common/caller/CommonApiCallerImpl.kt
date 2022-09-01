package spring.works.hook.common.caller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import spring.works.hook.util.error.ErrorCode
import spring.works.hook.util.error.MyException

@Component
class CommonApiCallerImpl : CommonApiCaller {

    @Autowired
    private lateinit var webClient: WebClient

    override fun sendApi(uri: String, data: Any) {
        webClient.post()
            .uri(uri)
            .accept(MediaType.ALL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(data)
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError) { Mono.error(MyException(ErrorCode.CLIENT_API_CALLER)) }
            .onStatus(HttpStatus::is5xxServerError) { Mono.error(MyException(ErrorCode.SERVER_API_CALLER)) }
            .bodyToMono<String>()
            .block()
    }
}
