package com.example.spring.works.hook.mapper

import com.example.kotlinserver.domain.member.Gender
import com.example.kotlinserver.domain.member.Member
import com.example.kotlinserver.domain.score.Score
import com.example.kotlinserver.domain.score.ScoreMapper
import com.example.kotlinserver.domain.score.Subject
import com.example.kotlinserver.repository.MemberRepository
import com.example.kotlinserver.repository.ScoreRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ScoreMapStructTests(
    @Autowired val scoreRepository: ScoreRepository,
    @Autowired val memberRepository: MemberRepository
) {

    val converter: ScoreMapper = Mappers.getMapper(ScoreMapper::class.java)

    private final val member: Member = Member(null, "Hannah", 20, Gender.MALE)
    private final val score: Score = Score(null, member, Subject.SCIENCE, 88)

    @Test
    fun toScoreDtoTests() {
        // given
        val saveMember = memberRepository.save(member)
        val saveScore = scoreRepository.save(score)

        // when
        val scoreDTO = converter.toScoreDto(saveScore, saveMember)

        // then
        assertThat(scoreDTO.id).isEqualTo(null)
        assertThat(scoreDTO.memberId).isEqualTo(saveMember.id)
    }
}
