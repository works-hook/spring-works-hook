package spring.works.hook.common.service

import org.springframework.stereotype.Service

@Service
class CommonService {

    fun findMenu(): String {
        return MENU_DATA
    }

    private val MENU_DATA = "{\"blocks\": [{\"type\": \"header\",\"text\": {\"type\": \"plain_text\",\"text\": \"\uD83C\uDF1F 원하시는 메뉴를 선택해주세요!\"" +
        "}},{\"type\": \"divider\"},{\"type\": \"actions\",\"elements\": [{\"type\": \"button\",\"text\": {\"type\": \"plain_text\",\"text\": \"인기 검색 종목 확인하기\"" +
        "},\"style\": \"primary\",\"accessibility_label\": \"click!\",\"url\": \"http://ec2-54-215-190-73.us-west-1.compute.amazonaws.com/naver/stock/topSearchStock\"}," +
        "{\"type\": \"button\",\"text\": {\"type\": \"plain_text\",\"text\": \"오늘의 증시 확인하기\"},\"style\": \"danger\"," +
        "\"accessibility_label\": \"click!\",\"url\": \"http://ec2-54-215-190-73.us-west-1.compute.amazonaws.com/naver/stock/topSearchStock\"}]}]}"
}
