package spring.works.hook.naverStock.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import spring.works.hook.naverStock.caller.NaverStockApiCaller
import spring.works.hook.naverStock.dto.MarketMajorsResponseDto
import spring.works.hook.naverStock.dto.TopSearchResponseDto

@Service
class NaverStockService(
    private val naverStockApiCaller: NaverStockApiCaller
) {

    fun findTopStock(): String? {
        val responseDto = naverStockApiCaller.findTopSearchStock()
        return responseDto?.let { TopSearchResponseDto.formatData(it) }
    }

    @Scheduled(fixedDelay = 6_000_000)
    fun findMarket(): String? {
        val responseDto = naverStockApiCaller.findMarket()
        return responseDto?.let { MarketMajorsResponseDto.formatData(it) }
    }
}
