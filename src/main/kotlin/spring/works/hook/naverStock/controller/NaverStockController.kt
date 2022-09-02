package spring.works.hook.naverStock.controller

import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import spring.works.hook.naverStock.service.NaverStockService

@RestController
@RequestMapping("/naver/stock")
class NaverStockController {

    @Autowired
    private lateinit var naverStockService: NaverStockService

    @PostMapping("/topSearchStock")
    fun topSearchStock(): JSONObject {
        val findTopStock = naverStockService.findTopStock()
        return JSONObject(findTopStock)
    }
}
