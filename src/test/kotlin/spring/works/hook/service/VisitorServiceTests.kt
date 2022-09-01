package spring.works.hook.service

import com.example.kotlinserver.domain.member.Gender
import com.example.kotlinserver.domain.member.Member
import com.example.kotlinserver.domain.visitor.Visitor
import com.example.kotlinserver.dto.visitor.VisitorDTO
import com.example.kotlinserver.dto.visitor.request.RequestVisitorDTO
import com.example.kotlinserver.dto.visitor.response.ResponseVisitorDTO
import com.example.kotlinserver.dto.visitor.response.ResponseVisitorUserDTO
import com.example.kotlinserver.repository.MemberRepository
import com.example.kotlinserver.repository.visitor.VisitorRepository
import com.example.kotlinserver.service.visitor.VisitorServiceImpl
import com.example.kotlinserver.util.error.ErrorCode
import com.example.kotlinserver.util.error.MyException
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk

class VisitorServiceTests : BehaviorSpec({

    afterContainer {
        clearAllMocks()
    }

    val memberRepository = mockk<MemberRepository>(relaxed = true, relaxUnitFun = true)
    val visitorRepository = mockk<VisitorRepository>(relaxed = true, relaxUnitFun = true)
    val visitorService = VisitorServiceImpl(visitorRepository, memberRepository)

    Given("visitor 데이터가 단건 존재하는 상태에서") {
        every { memberRepository.saveAll(listOf(getMember(1), getMember(2), getMember(3), getMember(10))) }
        every { visitorRepository.save(getVisitor()) }

        When("visitor 데이터에 중복 되지 않은 userId를 넣었을 때") {
            val request = RequestVisitorDTO(1, 10)
            every { visitorService.addVisitorUser(request) }

            Then("visitor userIds에 추가되어야 한다") {
                every { visitorService.selectOneVisitorList(request) } returns getResponseVisitorDTO()
            }
        }

        When("visitor 데이터에 중복된 userId를 넣었을 때") {
            Then("에러 코드를 반환한다") {
                val request = RequestVisitorDTO(1, 1)
                every { visitorService.addVisitorUser(request) } throws MyException(ErrorCode.EXIST_VISITOR)
            }
        }

        When("visitor 데이터에 존재하지 않은 userId를 넣었을 때") {
            Then("에러 코드를 반환한다") {
                val request = RequestVisitorDTO(1, 100)
                every { visitorService.addVisitorUser(request) } throws MyException(ErrorCode.NOT_EXIST_VISITOR)
            }
        }
    }

    Given("visitor 데이터가 존재하지 않은 상태에서") {
    }
}) {
    companion object {
        val getVisitor: () -> Visitor = {
            Visitor(
                id = 1,
                userIds = listOf(3, 1, 2)
            )
        }

        val getVisitorDTO: () -> VisitorDTO = {
            getVisitor().toDTO()
        }

        val getResponseVisitorDTO: () -> ResponseVisitorDTO = {
            ResponseVisitorDTO(
                1,
                listOf(
                    ResponseVisitorUserDTO(1, 21, Gender.FEMALE),
                    ResponseVisitorUserDTO(2, 22, Gender.FEMALE),
                    ResponseVisitorUserDTO(3, 23, Gender.FEMALE),
                    ResponseVisitorUserDTO(10, 30, Gender.FEMALE),
                )
            )
        }

        val getMember: (Int) -> Member = {
            Member(
                id = it.toLong(),
                userNickName = "nickname$it",
                age = 20 + it,
                gender = Gender.FEMALE
            )
        }

        fun myException(): MyException {
            return MyException(ErrorCode.INTERNAL_SYSTEM_ERROR)
        }
    }
}
