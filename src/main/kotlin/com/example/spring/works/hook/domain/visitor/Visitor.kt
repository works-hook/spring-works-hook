package com.example.spring.works.hook.domain.visitor

import com.example.spring.works.hook.dto.visitor.VisitorDTO
import com.vladmihalcea.hibernate.type.json.JsonType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@TypeDef(name = "json", typeClass = JsonType::class)
class Visitor(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long?,

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private var userIds: List<Long> = ArrayList()
) {
    fun toDTO(): VisitorDTO {
        return VisitorDTO(id, userIds)
    }

    fun addUserList(userId: Long) {
        userIds = userIds.plus(userId)
    }
}
