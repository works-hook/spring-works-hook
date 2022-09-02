package spring.works.hook.naverStock.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
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

    @Scheduled(fixedDelay = 6_000_000)
    fun findTopStock(): String {
        val responseDto = topStockApiCaller.findTopSearchStock()

        var resultData = ""
        if (responseDto != null) {
            resultData = TopSearchResponseDto.formatData(responseDto)
            slackApiCaller.sendApi(SLACK_TEST_URL, resultData)
        }
        return resultData
    }
}
