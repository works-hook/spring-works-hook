package com.example.spring.works.hook.controller

import com.example.spring.works.hook.dto.visitor.VisitorDTO
import com.example.spring.works.hook.dto.visitor.request.RequestVisitorDTO
import com.example.spring.works.hook.service.visitor.VisitorService
import com.example.spring.works.hook.util.ApiResponse.Success
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class VisitorController(private val visitorService: VisitorService) {

    @PostMapping(value = ["/visitor"], produces = ["application/json"])
    fun save(@RequestBody visitorDTO: VisitorDTO): Success<VisitorDTO> {
        val saveVisitor = visitorService.saveVisitor(visitorDTO)
        return Success(saveVisitor, "방문자 리스트가 저장되었습니다.")
    }

    @PatchMapping(value = ["/visitor"], produces = ["application/json"])
    fun addUser(@RequestBody request: RequestVisitorDTO): Success<VisitorDTO> {
        val saveVisitor = visitorService.addVisitorUser(request)
        return Success(saveVisitor, "방문자 리스트 추가가 완료되었습니다.")
    }
}
