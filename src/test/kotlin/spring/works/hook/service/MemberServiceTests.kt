package spring.works.hook.service

import com.example.kotlinserver.domain.member.Gender
import com.example.kotlinserver.dto.member.MemberDTO
import com.example.kotlinserver.dto.member.response.MemberResponseDTO
import com.example.kotlinserver.repository.MemberRepository
import com.example.kotlinserver.service.member.MemberService
import com.example.kotlinserver.service.member.MemberServiceImpl
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk

class MemberServiceTests : BehaviorSpec({

    afterContainer {
        clearAllMocks()
    }

    val memberRepository = mockk<MemberRepository>(relaxed = true, relaxUnitFun = true)
    val memberService: MemberService = MemberServiceImpl(memberRepository)

    Given("member 데이터가 2건 존재하는 상태에서") {
        givenInit(memberService)

        When("member 전체를 조회할 때") {
            val members = listOf(getMemberResponseDTO(1), getMemberResponseDTO(2))
            every { memberService.findMembers() } returns members

            Then("list의 size는 2이어야 한다") {
                members.size shouldBe 2
            }
        }

        When("member 단건을 조회할 때") {
            val memberDTO = getMemberDTO(1)
            val memberResponseDTO = getMemberResponseDTO(1)
            every { memberService.findMember(1L) } returns memberResponseDTO

            Then("처음 저장한 member와 같아야 한다") {
                memberResponseDTO.userNickName shouldBe memberDTO.userNickName
                memberResponseDTO.age shouldBe memberDTO.age
                memberResponseDTO.gender shouldBe memberDTO.gender
            }
        }

        When("처음 저장한 member를 수정할 때") {
            val memberDTO = getMemberDTO(3)
            val memberResponseDTO = MemberResponseDTO(1L, "nickname3", 23, Gender.FEMALE)
            every { memberService.updateMember(1L, memberDTO) } returns memberResponseDTO

            Then("member가 수정되어야 한다") {
                memberDTO.userNickName shouldBe memberResponseDTO.userNickName
                memberDTO.age shouldBe memberResponseDTO.age
            }
        }
    }
}
) {
    companion object {
        private fun getMemberDTO(i: Int): MemberDTO {
            return MemberDTO("nickname$i", 20 + i, Gender.FEMALE)
        }

        private fun getMemberResponseDTO(i: Int): MemberResponseDTO {
            return MemberResponseDTO(i.toLong(), "nickname$i", 20 + i, Gender.FEMALE)
        }

        fun givenInit(memberService: MemberService) {
            val member1 = getMemberDTO(1)
            val member2 = getMemberDTO(2)
            every { memberService.registerMember(member1) }
            every { memberService.registerMember(member2) }
        }
    }
}
