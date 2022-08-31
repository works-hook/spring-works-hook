package com.example.spring.works.hook.schedule.service

import com.example.spring.works.hook.schedule.caller.LocalTimeApiCaller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ScheduledTestService {

    @Autowired
    private lateinit var localApiCaller: LocalTimeApiCaller

    @Scheduled(fixedDelay = 60_000) // 1분
    fun testScheduled() {
        println(" > in scheduled")
        localApiCaller.testPrintNowDate()
    }

}