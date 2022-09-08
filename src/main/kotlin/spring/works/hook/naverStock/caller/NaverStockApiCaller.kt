package spring.works.hook.naverStock.caller

import spring.works.hook.naverStock.dto.ExchangeRateResponseDto
import spring.works.hook.naverStock.dto.MarketMajorsResponseDto
import spring.works.hook.naverStock.dto.TopSearchResponseDto

interface NaverStockApiCaller {

    fun findTopSearchStock(): MutableList<TopSearchResponseDto>?

    fun findMarket(): MutableList<MarketMajorsResponseDto>?

    fun findExchangeRate(): MutableList<ExchangeRateResponseDto>?
}
