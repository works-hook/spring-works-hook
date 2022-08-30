package com.example.spring.works.hook.repository

import com.example.kotlinserver.domain.author.Author
import com.example.kotlinserver.domain.author.Book
import com.example.kotlinserver.domain.member.Gender
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorRepositoryTests(
    @Autowired val authorRepository: AuthorRepository
) {

    val books: ArrayList<Book> = arrayListOf()

    @BeforeEach
    fun initBooks() {
        for (i in 1 until 10) {
            val book = Book(title = "title$i", price = 10000 + i)
            books.add(book)
        }
    }

    @AfterEach
    fun clear() {
        authorRepository.deleteAll()
    }

    @Test
    fun `author json save test`() {

        /*every {  } returns listOf<>()
        every {  } just Runs
        every {  } throws Illega()

        invoking{ } shouldThrow ser()

        verify(exactly = 1) {  aaa.xxx() }*/

        val author = Author(name = "name", gender = Gender.FEMALE, age = 20, books = books)
        authorRepository.save(author)
    }

    @Test
    fun `찾아서 더하기`() {
        val author = Author(name = "name", gender = Gender.FEMALE, age = 20, books = books)
        authorRepository.save(author)

        val authorFind = authorRepository.findByName("name").get()

        authorFind.addBook(Book("sp", "spTitle", 999999999))
        authorRepository.save(authorFind)

        for (a in authorRepository.findAll()) {
            println(":::::::::::::::::::$a")
        }
    }

    @Test
    fun `author json find test`() {
        for (author in authorRepository.findAll()) {
            println("author : ${author.toAuthorDto()}")
        }
    }
}
