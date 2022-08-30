package com.example.spring.works.hook.repository.visitor

import com.example.spring.works.hook.domain.visitor.QVisitor.visitor
import com.example.spring.works.hook.domain.visitor.Visitor
import com.example.spring.works.hook.dto.visitor.request.RequestVisitorDTO
import com.example.spring.works.hook.dto.visitor.response.ResponseVisitorDTO
import com.querydsl.jpa.impl.JPAQueryFactory
import java.util.Optional

class VisitorCustomRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : VisitorCustomRepository {

    override fun findByVisitorIdAndUserId(request: RequestVisitorDTO): Optional<Visitor> {
        return Optional.ofNullable(
            queryFactory.selectFrom(visitor).where(visitor.id.eq(request.visitorId), visitor.userIds.contains(request.userId)).fetchOne()
        )
    }

    override fun findByAllId(visitorId: Long): ResponseVisitorDTO? {
        queryFactory.select()
            .from()
        TODO("Not yet implemented")
    }

    override fun findByAllList(): List<ResponseVisitorDTO>? {
        TODO("Not yet implemented")
    }
}
