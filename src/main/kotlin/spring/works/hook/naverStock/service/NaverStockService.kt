package spring.works.hook.naverStock.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import spring.works.hook.naverStock.caller.NaverStockApiCaller
import spring.works.hook.naverStock.dto.TopSearchResponseDto

@Service
class NaverStockService {

    @Autowired
    private lateinit var topStockApiCaller: NaverStockApiCaller

    fun findTopStock(): String? {
        val responseDto = topStockApiCaller.findTopSearchStock()
        return responseDto?.let { TopSearchResponseDto.formatData(it) }
    }
}
