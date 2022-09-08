package spring.works.hook.naverStock.dto

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class ExchangeRateResponseDto(
    private val name: String?,
    private val closePrice: String?,
    private val fluctuationsRatio: String?
) {
    companion object {
        fun formatData(responseListDto: MutableList<ExchangeRateResponseDto>): String {
            val stringBuilder = StringBuilder()
            firstSetting(stringBuilder)
            middleSetting(responseListDto, stringBuilder)
            finalSetting(stringBuilder)
            return stringBuilder.toString()
        }

        private fun firstSetting(stringBuilder: StringBuilder) {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss")
            val nowDateFormat = LocalDateTime.now().format(formatter)
            stringBuilder.append("{\"blocks\": [{\"type\": \"header\",\"text\": {\"type\": \"plain_text\",\"text\": \"\uD83E\uDD16 현재 환율은?\",")
            stringBuilder.append("\"emoji\": true}},{\"type\": \"context\",\"elements\": [{\"text\": \"*$nowDateFormat* | works hook\",\"type\": \"mrkdwn\"\n")
            stringBuilder.append("}]},{\"type\": \"divider\"},{\"type\": \"section\",\"fields\": [")
        }

        private fun middleSetting(responseListDto: MutableList<ExchangeRateResponseDto>, stringBuilder: StringBuilder) {
            for (responseDto in responseListDto) {
                stringBuilder.append("{\"type\": \"mrkdwn\",\"text\": \"*")
                stringBuilder.append("${ responseDto.name }*\\n${ responseDto.closePrice }\\t\\t\\t")
                stringBuilder.append("${ getFluctuationsRatio(responseDto.fluctuationsRatio) }\"},")
            }
        }

        private fun finalSetting(stringBuilder: StringBuilder) {
            stringBuilder.append( "]}]}")
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