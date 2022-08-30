package com.example.spring.works.hook.service.member

import com.example.spring.works.hook.dto.member.MemberDTO
import com.example.spring.works.hook.dto.member.response.MemberResponseDTO

interface MemberService {
    fun registerMember(memberRegisterRequestDTO: MemberDTO): MemberResponseDTO
    fun findMembers(): List<MemberResponseDTO>
    fun findMember(memberId: Long): MemberResponseDTO
    fun updateMember(memberId: Long, requestDTO: MemberDTO): MemberResponseDTO
    fun deleteMember(memberId: Long)
}
