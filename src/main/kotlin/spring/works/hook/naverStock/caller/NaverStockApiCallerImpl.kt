package spring.works.hook.naverStock.caller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import spring.works.hook.naverStock.dto.MarketMajorsResponseDto
import spring.works.hook.naverStock.dto.TopSearchResponseDto
import spring.works.hook.naverStock.service.getMarketResult
import spring.works.hook.naverStock.service.getTopSearchResult
import spring.works.hook.util.error.ErrorCode
import spring.works.hook.util.error.MyException

@Component
class NaverStockApiCallerImpl(
    private val webClient: WebClient
) : NaverStockApiCaller {

    @Value("\${naver.top.stock.url}")
    private val NAVER_TOP_STOCK_URL: String = ""

    @Value("\${m.naver.stock.main}")
    private val M_NAVER_STOCK_MAIN: String = ""

    override fun findTopSearchStock(): MutableList<TopSearchResponseDto>? {
        val resultString = webClient.get()
            .uri(NAVER_TOP_STOCK_URL)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError) { Mono.error(MyException(ErrorCode.CLIENT_API_CALLER)) }
            .onStatus(HttpStatus::is5xxServerError) { Mono.error(MyException(ErrorCode.SERVER_API_CALLER)) }
            .bodyToMono<String>()
            .block()
        return resultString.getTopSearchResult()
    }

    override fun findMarket(): MutableList<MarketMajorsResponseDto>? {
        val resultString = webClient.get()
            .uri(M_NAVER_STOCK_MAIN)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError) { Mono.error(MyException(ErrorCode.CLIENT_API_CALLER)) }
            .onStatus(HttpStatus::is5xxServerError) { Mono.error(MyException(ErrorCode.SERVER_API_CALLER)) }
            .bodyToMono<String>()
            .block()
        return resultString.getMarketResult()
    }
}
