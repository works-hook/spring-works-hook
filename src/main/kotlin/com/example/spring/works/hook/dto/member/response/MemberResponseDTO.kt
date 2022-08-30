package com.example.spring.works.hook.dto.member.response

import com.example.spring.works.hook.domain.member.Gender

data class MemberResponseDTO(
    val id: Long?,
    val userNickName: String?,
    val age: Int?,
    val gender: Gender?
)
