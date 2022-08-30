package com.example.spring.works.hook.dto.visitor

import com.example.spring.works.hook.domain.visitor.Visitor

data class VisitorDTO(
    val visitorId: Long?,
    val userIds: List<Long>
) {
    fun toEntity(): Visitor {
        return Visitor(visitorId, userIds)
    }
}
