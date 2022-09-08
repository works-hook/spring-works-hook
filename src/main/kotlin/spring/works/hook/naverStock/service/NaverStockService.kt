package spring.works.hook.naverStock.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import spring.works.hook.common.caller.CommonApiCaller
import spring.works.hook.naverStock.caller.NaverStockApiCaller
import spring.works.hook.naverStock.dto.MarketMajorsResponseDto
import spring.works.hook.naverStock.dto.TopSearchResponseDto

@Service
class NaverStockService(
    private val naverStockApiCaller: NaverStockApiCaller,
    private val commonApiCaller: CommonApiCaller
) {

    @Value("\${slack.test.url}")
    private val M_NAVER_STOCK_MAIN: String = ""

    fun findTopStock(): String? {
        val responseDto = naverStockApiCaller.findTopSearchStock()
        return responseDto?.let { TopSearchResponseDto.formatData(it) }
    }

    @Scheduled(fixedDelay = 6_000_000)
    fun findMarket(): String? {
        val responseDto = naverStockApiCaller.findMarket()
        val result = responseDto?.let { MarketMajorsResponseDto.formatData(it) }
        if (result != null) {
            commonApiCaller.sendApi(M_NAVER_STOCK_MAIN, result)
        }
        return result
    }
}
