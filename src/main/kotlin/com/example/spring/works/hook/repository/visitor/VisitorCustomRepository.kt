package com.example.spring.works.hook.repository.visitor

import com.example.spring.works.hook.domain.visitor.Visitor
import com.example.spring.works.hook.dto.visitor.request.RequestVisitorDTO
import com.example.spring.works.hook.dto.visitor.response.ResponseVisitorDTO
import java.util.Optional

interface VisitorCustomRepository {
    fun findByVisitorIdAndUserId(request: RequestVisitorDTO): Optional<Visitor>
    fun findByAllId(visitorId: Long): ResponseVisitorDTO?
    fun findByAllList(): List<ResponseVisitorDTO>?
}
