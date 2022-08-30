package com.example.spring.works.hook.controller

import com.example.kotlinserver.domain.member.Gender
import com.example.kotlinserver.dto.author.AuthorDTO
import com.example.kotlinserver.dto.author.BookDTO
import com.example.kotlinserver.service.author.AuthorService
import com.example.kotlinserver.util.ApiResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerTests(
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper,
    @Autowired val authorService: AuthorService
) {

    // 통합 테스트

    @BeforeEach
    fun init() {
        authorService.registerAuthor(getAuthorDTO())
    }

    @Test
    fun `find one author`() {
        val success = ApiResponse.Success(getAuthorDTO(), "작가 & 책 목록입니다.")

        mockMvc.get("/author/1")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    json(objectMapper.writeValueAsString(success))
                }
            }
    }

    val getAuthorDTO: () -> AuthorDTO = {
        AuthorDTO(
            id = 1L,
            name = "Hannah",
            gender = Gender.FEMALE,
            age = 20,
            books = listOf(
                BookDTO("string-id-1", "title1", 12900),
                BookDTO("string-id-2", "title2", 13900),
                BookDTO("string-id-3", "title3", 14900)
            )
        )
    }
}
