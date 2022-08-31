package com.example.spring.works.hook.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
internal class WebClientConfig {

    @Value("\${slack-test-url}")
    private val SLACK_TEST_URL = ""

    @Bean
    fun webClient(builder: WebClient.Builder): WebClient {
        return builder
            .baseUrl(SLACK_TEST_URL)
            .build()
    }
}