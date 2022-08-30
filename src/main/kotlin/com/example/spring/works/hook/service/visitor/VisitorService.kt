package com.example.spring.works.hook.service.visitor

import com.example.spring.works.hook.dto.visitor.VisitorDTO
import com.example.spring.works.hook.dto.visitor.request.RequestVisitorDTO
import com.example.spring.works.hook.dto.visitor.response.ResponseVisitorDTO

interface VisitorService {

    fun saveVisitor(request: VisitorDTO): VisitorDTO

    fun addVisitorUser(request: RequestVisitorDTO): VisitorDTO

    fun selectOneVisitorList(request: RequestVisitorDTO): ResponseVisitorDTO

    fun selectVisitorAllList(): List<ResponseVisitorDTO>
}
