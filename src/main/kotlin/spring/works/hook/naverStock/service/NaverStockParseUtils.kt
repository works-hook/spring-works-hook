package spring.works.hook.naverStock.service

import org.json.JSONArray
import org.json.JSONObject
import spring.works.hook.naverStock.dto.ExchangeRateResponseDto
import spring.works.hook.naverStock.dto.MarketMajorsResponseDto
import spring.works.hook.naverStock.dto.TopSearchResponseDto

fun String?.getMarketResult(): MutableList<MarketMajorsResponseDto> {
    val majorsDto = JSONObject(this).getJSONArray("homeMajors")
    return getMarketResult(majorsDto)
}

fun getMarketResult(majorsDto: JSONArray): MutableList<MarketMajorsResponseDto> {
    val result = mutableListOf<MarketMajorsResponseDto>()
    for (major in majorsDto) {
        if (major is JSONObject) { result.add(major.toMarketDto) }
    }
    return result
}

val JSONObject.toMarketDto: MarketMajorsResponseDto
    get() {
        val imageCharts = this.get("imageCharts") as JSONObject
        return MarketMajorsResponseDto(
            name = this.get("name") as String?,
            closePrice = this.get("closePrice") as String?,
            fluctuationsRatio = this.get("fluctuationsRatio") as String?,
            imageCharts = imageCharts.get("mini") as String?,
            delayTimeName = this.get("delayTimeName") as String?
        )
    }

fun String?.getTopSearchResult(): MutableList<TopSearchResponseDto>? {
    val resultObject = JSONObject(this?.substring(31))
    val messageDto = resultObject.getJSONObject("message")
    val resultDto = messageDto?.getJSONObject("result")
    val searchDto = resultDto?.getJSONArray("searchList")
    return searchDto?.let { getSearchListDto(it) }
}

fun getSearchListDto(searchDto: JSONArray): MutableList<TopSearchResponseDto> {
    val result = mutableListOf<TopSearchResponseDto>()
    for (search in searchDto) {
        if (search is JSONObject) { result.add(search.toSearchListDto) }
    }
    return result
}

val JSONObject.toSearchListDto: TopSearchResponseDto
    get() {
        return TopSearchResponseDto(
            nowVal = this.get("nowVal") as String?,
            risefall = this.get("risefall") as String?,
            itemName = this.get("itemname") as String?,
            itemCode = this.get("itemcode") as String?,
            sosok = this.get("sosok") as String?,
            changeVal = this.get("changeVal") as String?,
            changeRate = this.get("changeRate") as String?,
        )
    }

fun String?.getExchangeResult(): MutableList<ExchangeRateResponseDto>? {
    val exchangeDto = JSONObject(this).getJSONArray("marketIndexInfos")
    return getExchangeResult(exchangeDto)
}

fun getExchangeResult(exchangeDto: JSONArray): MutableList<ExchangeRateResponseDto> {
    val result = mutableListOf<ExchangeRateResponseDto>()
    for (exchange in exchangeDto) {
        if (exchange is JSONObject) { result.add(exchange.exchangeDto) }
    }
    return result
}

val JSONObject.exchangeDto: ExchangeRateResponseDto
    get() {
        return ExchangeRateResponseDto(
            name = this.get("name") as String?,
            closePrice = this.get("closePrice") as String?,
            fluctuationsRatio = this.get("fluctuationsRatio") as String?
        )
    }
