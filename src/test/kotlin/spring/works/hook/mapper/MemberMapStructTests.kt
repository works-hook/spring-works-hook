package spring.works.hook.mapper

import com.example.kotlinserver.domain.member.Gender
import com.example.kotlinserver.domain.member.Member
import com.example.kotlinserver.domain.member.MemberMapper
import com.example.kotlinserver.dto.member.MemberDTO
import com.example.kotlinserver.dto.member.response.MemberResponseDTO
import com.example.kotlinserver.repository.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberMapStructTests(
    @Autowired val memberRepository: MemberRepository
) {

    val converter: MemberMapper = Mappers.getMapper(MemberMapper::class.java)

    @BeforeEach
    fun saveMember() {
        val member = Member(null, "Hannar", 20, Gender.FEMALE)
        memberRepository.save(member)
    }

    @Test
    fun toMemberResponseDTOTest() {
        // given
        val member = memberRepository.findById(1L)

        // when
        val memberDto = converter.toMemberResponseDTO(member.get())

        // then
        assertThat(memberDto).isEqualTo(getMemberResponseDto())
    }
    @Test
    fun toMemberDTOTest() {
        // given
        val member = memberRepository.findById(1L)

        // when
        val memberDto = converter.toMemberDTO(member.get())

        // then
        assertThat(memberDto).isEqualTo(getMemberDto())
    }

    private fun getMemberResponseDto(): MemberResponseDTO {
        return MemberResponseDTO(1L, "Hannar", 20, Gender.FEMALE)
    }

    private fun getMemberDto(): MemberDTO {
        return MemberDTO("Hannar", 20, Gender.FEMALE)
    }
}
