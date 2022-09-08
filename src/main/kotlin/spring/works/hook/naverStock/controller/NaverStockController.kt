package spring.works.hook.naverStock.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import spring.works.hook.naverStock.service.NaverStockService

@RestController
@RequestMapping("/naver/stock")
class NaverStockController(
    private val naverStockService: NaverStockService
) {

    @PostMapping("/topSearchStock")
    fun topSearchStock(): String? {
        return naverStockService.findTopStock()
    }

    @PostMapping("/market")
    fun market(): String? {
        return naverStockService.findMarket()
    }
}
