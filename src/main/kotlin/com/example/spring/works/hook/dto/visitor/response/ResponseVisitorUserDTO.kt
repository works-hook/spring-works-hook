package com.example.spring.works.hook.dto.visitor.response

import com.example.spring.works.hook.domain.member.Gender

data class ResponseVisitorUserDTO(
    private val userId: Long,
    private val age: Int,
    private val gender: Gender
)
