package spring.works.hook.common.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import spring.works.hook.common.service.CommonService

@RestController
@RequestMapping("/common")
class CommonController {

    @Autowired
    private lateinit var commonService: CommonService

    @PostMapping("/menu")
    fun findMenu(): String {
        return commonService.findMenu()
    }

}