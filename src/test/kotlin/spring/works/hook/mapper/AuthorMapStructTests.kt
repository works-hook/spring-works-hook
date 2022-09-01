package spring.works.hook.mapper

import com.example.kotlinserver.domain.author.Author
import com.example.kotlinserver.domain.author.AuthorMapper
import com.example.kotlinserver.domain.author.Book
import com.example.kotlinserver.domain.member.Gender
import com.example.kotlinserver.repository.AuthorRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AuthorMapStructTests(
    @Autowired val authorRepository: AuthorRepository
) {

    val books: ArrayList<Book> = arrayListOf()
    val converter: AuthorMapper = Mappers.getMapper(AuthorMapper::class.java)

    @BeforeEach
    fun init() {
        for (i in 1 until 10) {
            val book = Book(title = "title$i", price = 10000 + i)
            books.add(book)
        }
        authorRepository.save(Author(name = "name", gender = Gender.FEMALE, age = 20, books = books))
    }

    @Test
    fun toAuthorDtoTests() {
        val author = authorRepository.findById(1L)

        val authorDto = converter.toAuthorDto(author.get())

        println(authorDto)
    }
}
