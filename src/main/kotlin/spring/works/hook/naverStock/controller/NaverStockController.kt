package spring.works.hook.naverStock.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.BodyBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import spring.works.hook.naverStock.service.NaverStockService

@RestController
@RequestMapping("/naver/stock")
class NaverStockController {

    @Autowired
    private lateinit var naverStockService: NaverStockService

    @GetMapping("/topSearchStock")
    fun topSearchStock(): BodyBuilder {
        naverStockService.findTopStock()
        return ResponseEntity.ok()
    }
}
