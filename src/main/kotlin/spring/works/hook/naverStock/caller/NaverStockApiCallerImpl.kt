package spring.works.hook.naverStock.caller

import org.json.JSONArray
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import spring.works.hook.naverStock.dto.MarketMajorsResponseDto
import spring.works.hook.naverStock.dto.TopSearchResponseDto
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
        return resultString?.let { getTopSearchResult(it) }
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
        return resultString?.let { getMarketResult(it) }
    }

    companion object {

        private fun getMarketResult(resultObject: String): MutableList<MarketMajorsResponseDto> {
            val majorsDto = JSONObject(resultObject).getJSONArray("homeMajors")
            return getMarketResult(majorsDto)
        }

        private fun getMarketResult(majorsDto: JSONArray): MutableList<MarketMajorsResponseDto> {
            val result = mutableListOf<MarketMajorsResponseDto>()
            for (major in majorsDto) {
                if (major is JSONObject) { result.add(major.toMarketDto) }
            }
            return result
        }

        private val JSONObject.toMarketDto: MarketMajorsResponseDto
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

        private fun getTopSearchResult(resultString: String): MutableList<TopSearchResponseDto>? {
            val resultObject = JSONObject(resultString.substring(31))
            val messageDto = resultObject.getJSONObject("message")
            val resultDto = messageDto?.getJSONObject("result")
            val searchDto = resultDto?.getJSONArray("searchList")
            return searchDto?.let { getSearchListDto(it) }
        }

        private fun getSearchListDto(searchDto: JSONArray): MutableList<TopSearchResponseDto> {
            val result = mutableListOf<TopSearchResponseDto>()
            for (search in searchDto) {
                if (search is JSONObject) { result.add(search.toSearchListDto) }
            }
            return result
        }

        private val JSONObject.toSearchListDto: TopSearchResponseDto
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
    }
}
