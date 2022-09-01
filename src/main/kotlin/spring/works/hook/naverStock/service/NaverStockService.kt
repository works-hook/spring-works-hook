package spring.works.hook.naverStock.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import spring.works.hook.common.caller.CommonApiCaller
import spring.works.hook.naverStock.caller.NaverStockApiCaller
import spring.works.hook.naverStock.dto.TopSearchResponseDto

@Service
class NaverStockService {

    @Value("\${slack.test.url}")
    private val SLACK_TEST_URL: String = ""

    @Autowired
    private lateinit var topStockApiCaller: NaverStockApiCaller

    @Autowired
    private lateinit var slackApiCaller: CommonApiCaller

    fun findTopStock() {
        val responseDto = topStockApiCaller.findTopSearchStock()
        responseDto?.let { slackApiCaller.sendApi(SLACK_TEST_URL, TopSearchResponseDto.formatData(it)) }
    }
}
