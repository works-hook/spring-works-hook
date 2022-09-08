package spring.works.hook.naverStock.dto

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class MarketMajorsResponseDto(
    private val name: String?,
    private val closePrice: String?,
    private val fluctuationsRatio: String?,
    private val imageCharts: String?,
    private val delayTimeName: String?
) {
    companion object {
        fun formatData(responseListDto: MutableList<MarketMajorsResponseDto>): String {
            val stringBuilder = StringBuilder()
            firstSetting(stringBuilder)
            middleSetting(responseListDto, stringBuilder)
            finalSetting(stringBuilder)
            return stringBuilder.toString()
        }

        private fun firstSetting(stringBuilder: StringBuilder) {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss")
            val nowDateFormat = LocalDateTime.now().format(formatter)
            stringBuilder.append("{\"blocks\": [{\"type\": \"header\",\"text\": {\"type\": \"plain_text\",\"text\": \"\uD83D\uDC7B 현재 증시는?\",")
            stringBuilder.append("\"emoji\": true}},{\"type\": \"context\",\"elements\": [{\"text\": \"*$nowDateFormat* | works hook\",\"type\": \"mrkdwn\"\n")
            stringBuilder.append("}]},{\"type\": \"divider\"},")
        }

        private fun middleSetting(responseListDto: MutableList<MarketMajorsResponseDto>, stringBuilder: StringBuilder) {
            for (responseDto in responseListDto) {
                stringBuilder.append("{\"type\": \"section\",\"text\": {\"type\": \"mrkdwn\",")
                stringBuilder.append("\"text\": \"*<test.com|${ responseDto.name }>")
                stringBuilder.append("*\\n*${ responseDto.closePrice }*\\n\\n${ getFluctuationsRatio(responseDto.fluctuationsRatio) }\"")
                stringBuilder.append("}},{\"type\": \"image\",\"title\": {\"type\": \"plain_text\",\"text\": \"${ responseDto.name }\",")
                stringBuilder.append("\"emoji\": true},\"image_url\": \"${ responseDto.imageCharts }\",")
                stringBuilder.append("\"alt_text\": \"${ responseDto.name }\"},{\"type\": \"context\",\"elements\": [\n")
                stringBuilder.append("{\"type\": \"image\",\"image_url\": \"https://api.slack.com/img/blocks/bkb_template_images/tripAgentLocationMarker.png\",")
                stringBuilder.append("\"alt_text\": \"Location Pin Icon\"},{\"type\": \"plain_text\",\"emoji\": true,")
                stringBuilder.append("\"text\": \"${ responseDto.delayTimeName }\"}]},{\"type\": \"divider\"},")
            }
        }

        private fun finalSetting(stringBuilder: StringBuilder) {
            stringBuilder.append("\t]}")
        }

        private fun getFluctuationsRatio(ratio: String?): String {
            val stringBuilder = StringBuilder()
            val toDouble = ratio?.toDouble()
            if (toDouble != null) {
                if (toDouble < 0) {
                    stringBuilder.append("\uD83D\uDFE6")
                } else {
                    stringBuilder.append("\uD83D\uDFE5")
                }
            }
            stringBuilder.append(" $ratio%")
            return stringBuilder.toString()
        }
    }
}
