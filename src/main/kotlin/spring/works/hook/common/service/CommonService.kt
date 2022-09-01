package spring.works.hook.common.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import spring.works.hook.common.caller.CommonApiCaller

@Service
class CommonService {

    @Value("\${slack.test.url}")
    private val SLACK_TEST_URL: String = ""

    @Autowired
    private lateinit var slackApiCaller: CommonApiCaller

    @Scheduled(fixedDelay = 6_000_000)
    fun findMenu() {
        slackApiCaller.sendApi(SLACK_TEST_URL, MENU_DATA)
    }

    private val MENU_DATA = "{\"blocks\": [{\"type\": \"header\",\"text\": {\"type\": \"plain_text\",\"text\": \"\uD83C\uDF1F 원하시는 메뉴를 선택해주세요!\"" +
        "}},{\"type\": \"divider\"},{\"type\": \"actions\",\"elements\": [{\"type\": \"button\",\"text\": {\"type\": \"plain_text\",\"text\": \"인기 검색 종목 확인하기\"" +
        "},\"style\": \"primary\",\"accessibility_label\": \"click!\",\"url\": \"http://localhost:8080/naver/stock/topSearchStock\"}," +
        "{\"type\": \"button\",\"text\": {\"type\": \"plain_text\",\"text\": \"오늘의 증시 확인하기\"},\"style\": \"danger\"," +
        "\"accessibility_label\": \"click!\",\"url\": \"https://api.slack.com/block-kit\"}]}]}"
}
