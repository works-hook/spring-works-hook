package spring.works.hook.naverStock.dto

import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class TopSearchResponseDto(
    val nowVal: String?,
    val risefall: String?,
    val itemName: String?,
    val itemCode: String?,
    val sosok: String?,
    val changeVal: String?,
    val changeRate: String?
) {
    companion object {
        fun formatData(responseListDto: MutableList<TopSearchResponseDto>): String {
            val stringBuilder = StringBuilder()
            firstSetting(stringBuilder)
            middleSetting(responseListDto, stringBuilder)
            finalSetting(stringBuilder)
            return stringBuilder.toString()
        }

        private fun firstSetting(stringBuilder: StringBuilder) {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss")
            val nowDateFormat = LocalDateTime.now().format(formatter)
            stringBuilder.append("{\"attachments\":[{\"color\":\"#007A5A\",\"blocks\":[{\"type\":\"header\",\"text\":{\"type\":\"plain_text\",")
            stringBuilder.append("\"text\":\"\uD83D\uDE0E 현재 인기 종목은?\"}},{\"type\":\"context\",\"elements\":[")
            stringBuilder.append("{\"text\":\"*$nowDateFormat* | works hook\",\"type\":\"mrkdwn\"}]},{\"type\":\"divider\"},")
        }

        private fun middleSetting(responseListDto: MutableList<TopSearchResponseDto>, stringBuilder: StringBuilder) {
            for (responseDto in responseListDto) {
                stringBuilder.append("{\"type\": \"actions\",\"elements\": [{\"type\": \"button\",\"text\": {")
                stringBuilder.append("\"type\": \"plain_text\",\"text\": \"${ responseDto.itemName }\"")
                stringBuilder.append("},\"style\": \"primary\"}]},{\"type\":\"section\",\"fields\":[")
                stringBuilder.append("{\"type\":\"mrkdwn\",\"text\": \"*종목 코드*\n`${ responseDto.itemCode }`\"")
                stringBuilder.append("},{\"type\":\"mrkdwn\",\"text\":\"*현재가*\n`${ responseDto.nowVal.comma() }`\"")
                stringBuilder.append("},{\"type\":\"mrkdwn\",\"text\":\"*전일비*\n`${ responseDto.changeVal.comma() }`\"")
                stringBuilder.append("},{\"type\":\"mrkdwn\",\"text\":\"*등락률*\n`${ responseDto.changeRate }%`\"")
                stringBuilder.append("}]},{\"type\":\"divider\"},")
            }
        }

        private fun finalSetting(stringBuilder: StringBuilder) {
            stringBuilder.append("]}]}")
        }

        private fun String?.comma(): String {
            val dec = DecimalFormat("###,###,###,###")
            return dec.format(this?.toInt())
        }
    }
}
