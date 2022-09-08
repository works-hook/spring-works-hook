package spring.works.hook.naverStock.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import spring.works.hook.common.caller.CommonApiCaller
import spring.works.hook.naverStock.caller.NaverStockApiCaller
import spring.works.hook.naverStock.dto.ExchangeRateResponseDto
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

    fun findMarket(): String? {
        val responseDto = naverStockApiCaller.findMarket()
        return responseDto?.let { MarketMajorsResponseDto.formatData(it) }
    }

    fun findExchangeRate(): String? {
        val responseDto = naverStockApiCaller.findExchangeRate()
        return responseDto?.let { ExchangeRateResponseDto.formatData(it) }
    }

}
