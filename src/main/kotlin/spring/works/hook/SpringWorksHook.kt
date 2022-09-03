package spring.works.hook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@EnableScheduling
@SpringBootApplication
class SpringWorksHook

fun main(args: Array<String>) {
    runApplication<SpringWorksHook>(*args)
}

@Controller
class SpringWorksHookController {

    @GetMapping(value = ["/ping"], produces = ["application/json"])
    fun ping(): String {
        return buildString {
            append("OK")
        }
    }

}
