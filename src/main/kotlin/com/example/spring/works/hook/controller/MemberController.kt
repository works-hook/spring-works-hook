package com.example.spring.works.hook.controller

import com.example.spring.works.hook.dto.member.MemberDTO
import com.example.spring.works.hook.dto.member.response.MemberResponseDTO
import com.example.spring.works.hook.service.member.MemberService
import com.example.spring.works.hook.util.ApiResponse.Success
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(private val memberService: MemberService) {

    @PostMapping(value = ["/member"], produces = ["application/json"])
    fun register(@RequestBody memberRegisterRequest: MemberDTO): Success<MemberResponseDTO> {
        val saveMember = memberService.registerMember(memberRegisterRequest)
        return Success(saveMember, "사용자 등록이 완료되었습니다.")
    }

    @GetMapping(value = ["/members"], produces = ["application/json"])
    fun findMembers(): Success<List<MemberResponseDTO>> {
        val findMembers = memberService.findMembers()
        return Success(findMembers, "사용자 목록입니다.")
    }

    @GetMapping(value = ["/member/{memberId}"], produces = ["application/json"])
    fun findMember(@PathVariable memberId: Long): Success<MemberResponseDTO> {
        val findMember = memberService.findMember(memberId)
        return Success(findMember, "사용자 상세입니다.")
    }

    @PatchMapping(value = ["/member/{memberId}"], produces = ["application/json"])
    fun updateMember(@PathVariable memberId: Long, requestDTO: MemberDTO): Success<MemberResponseDTO> {
        val updateMember = memberService.updateMember(memberId, requestDTO)
        return Success(updateMember, "사용자 수정이 완료되었습니다.")
    }

    @DeleteMapping(value = ["/member/{memberId}"], produces = ["application/json"])
    fun deleteMember(@PathVariable memberId: Long): Success<String> {
        memberService.deleteMember(memberId)
        return Success("OK", "사용자 삭제가 완료되었습니다.")
    }
}
