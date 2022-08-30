package com.example.spring.works.hook.service.member

import com.example.spring.works.hook.dto.member.MemberDTO
import com.example.spring.works.hook.dto.member.response.MemberResponseDTO
import com.example.spring.works.hook.repository.MemberRepository
import com.example.spring.works.hook.util.error.ErrorCode
import com.example.spring.works.hook.util.error.MyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class MemberServiceImpl(private val memberRepository: MemberRepository) : MemberService {

    override fun findMembers(): List<MemberResponseDTO> {
        val findMembers = memberRepository.findAll()
        return findMembers.map { member -> member.toResponseDTO() }
    }

    override fun findMember(memberId: Long): MemberResponseDTO {
        return memberRepository.findByIdOrNull(memberId)
            ?.toResponseDTO()
            ?: throw MyException(ErrorCode.NOT_EXIST_MEMBER)
    }

    @Transactional
    override fun registerMember(memberRegisterRequestDTO: MemberDTO): MemberResponseDTO {
        checkUserNickname(memberRegisterRequestDTO.userNickName)
        val saveMember = memberRepository.save(memberRegisterRequestDTO.toEntity())
        return saveMember.toResponseDTO()
    }

    @Transactional
    override fun updateMember(memberId: Long, requestDTO: MemberDTO): MemberResponseDTO {
        val findMember = memberRepository.findByIdOrNull(memberId)
            ?: throw MyException(ErrorCode.NOT_EXIST_MEMBER)
        findMember.changeInfo(requestDTO)
        return findMember.toResponseDTO()
    }

    @Transactional
    override fun deleteMember(memberId: Long) {
        val findMember = memberRepository.findByIdOrNull(memberId)
            ?: throw MyException(ErrorCode.NOT_EXIST_MEMBER)
        memberRepository.delete(findMember)
    }

    private fun checkUserNickname(userNickname: String) {
        if (memberRepository.findByUserNickName(userNickname).isEmpty)
            throw MyException(ErrorCode.EXIST_NICK_NAME)
    }
}
