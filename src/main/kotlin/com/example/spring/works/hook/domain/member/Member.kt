package com.example.spring.works.hook.domain.member

import com.example.spring.works.hook.domain.util.BaseEntity
import com.example.spring.works.hook.dto.member.MemberDTO
import com.example.spring.works.hook.dto.member.response.MemberResponseDTO
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Member(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var userNickName: String,
    var age: Int,

    @Enumerated(EnumType.STRING)
    var gender: Gender

) : BaseEntity() {
    fun toResponseDTO(): MemberResponseDTO {
        return MemberResponseDTO(
            id = this.id,
            userNickName = this.userNickName,
            age = this.age,
            gender = this.gender
        )
    }

    fun changeInfo(requestDTO: MemberDTO) {
        userNickName = requestDTO.userNickName
        age = requestDTO.age
        gender = requestDTO.gender
    }
}

enum class Gender {
    FEMALE, MALE
}
