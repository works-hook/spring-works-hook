package spring.works.hook.service

import com.example.kotlinserver.domain.member.Gender
import com.example.kotlinserver.dto.author.AuthorDTO
import com.example.kotlinserver.dto.author.BookDTO
import com.example.kotlinserver.repository.AuthorRepository
import com.example.kotlinserver.service.author.AuthorService
import com.example.kotlinserver.service.author.AuthorServiceImpl
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk

class AuthorServiceTests : BehaviorSpec({

    afterContainer {
        clearAllMocks()
    }

    val authorRepository = mockk<AuthorRepository>(relaxed = true, relaxUnitFun = true)
    val authorService: AuthorService = AuthorServiceImpl(authorRepository)

    Given("author 데이터가 단건만 존재하는 상태에서") {
        val author: AuthorDTO = getAuthorDTO(1)
        every { authorService.registerAuthor(author) } returns author

        When("저장된 author를 단건 조회했을 때") {
            Then("저장한 author와 조회한 author가 같아야 한다") {
                val result = getAuthorDTO(1)
                every { author.id?.let { authorService.findAuthor(it) } } returns result
            }
        }

        When("모든 author list 를 조회했을 때") {
            val result = listOf(author)
            every { authorService.findAuthors() } returns result

            Then("list size 는 1개여야 한다") {
                result.size shouldBe 1
            }
        }
    }
}) {
    companion object {
        val getAuthorDTO: (Int) -> AuthorDTO = {
            AuthorDTO(
                id = it + 0L,
                name = "Hannah$it",
                gender = Gender.FEMALE,
                age = 20 + it,
                books = listOf(
                    BookDTO("string-id-$it", "title$it", 12900),
                    BookDTO("string-id-$it", "title$it", 12900),
                    BookDTO("string-id-$it", "title$it", 12900)
                )
            )
        }
    }
}
