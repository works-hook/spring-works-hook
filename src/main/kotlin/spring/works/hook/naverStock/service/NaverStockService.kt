package spring.works.hook.naverStock.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import spring.works.hook.common.caller.SlackApiCaller
import spring.works.hook.naverStock.caller.NaverStockApiCaller
import spring.works.hook.naverStock.dto.topSearch.TopSearchResponseDto

@Service
class NaverStockService(
    @field:Value("\${slack.test.url}") private val SLACK_TEST_URL: String = ""
) {

    @Autowired
    private lateinit var topStockApiCaller: NaverStockApiCaller

    @Autowired
    private lateinit var slackApiCaller: SlackApiCaller

    @Scheduled(fixedDelay = 60_000)
    fun findTopStock() {
        val responseDto = topStockApiCaller.findTopSearchStock()
        responseDto?.let { slackApiCaller.sendSlackBot(
            SLACK_TEST_URL,
            TopSearchResponseDto.formatData(it)
        )}
    }

}
