package spring.works.hook.config

import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Configuration
internal class WebClientConfig {

    private val logger = KotlinLogging.logger {}

    @Bean
    fun webClient(builder: WebClient.Builder): WebClient {
        return builder
            .filters { exchangeFilterFunctions ->
                exchangeFilterFunctions.add(logRequest())
                exchangeFilterFunctions.add(logResponse())
            }
            .build()
    }

    private fun logRequest(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofRequestProcessor { request ->
            logger.info("---------> Request: [{}] {}", request.method(), request.url())
            return@ofRequestProcessor Mono.just(request)
        }
    }

    private fun logResponse(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofResponseProcessor { response ->
            logger.info("---------> Response: {}", response.statusCode())
            return@ofResponseProcessor Mono.just(response)
        }
    }
}
