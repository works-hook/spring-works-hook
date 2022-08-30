package com.example.spring.works.hook.domain.score

import com.example.spring.works.hook.domain.member.Member
import com.example.spring.works.hook.domain.util.BaseEntity
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Score(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    @Enumerated(EnumType.STRING)
    val subject: Subject,

    val score: Int,

) : BaseEntity()

enum class Subject {
    KOREAN, MATH, ENGLISH, SCIENCE
}
