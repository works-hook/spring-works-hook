package spring.works.hook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class SpringWorksHook

fun main(args: Array<String>) {
    runApplication<SpringWorksHook>(*args)
}
