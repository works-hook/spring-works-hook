package spring.works.hook.naverStock.caller

import spring.works.hook.naverStock.dto.topSearch.TopSearchResponseDto

interface NaverStockApiCaller {

    fun findTopSearchStock(): MutableList<TopSearchResponseDto>?
}