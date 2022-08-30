package com.example.spring.works.hook.dto.visitor.response

data class ResponseVisitorDTO(
    private val visitorId: Long?,
    private val users: List<ResponseVisitorUserDTO>
)
