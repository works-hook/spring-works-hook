package com.example.spring.works.hook.controller


import com.example.spring.works.hook.dto.author.AuthorDTO
import com.example.spring.works.hook.dto.author.BookDTO
import com.example.spring.works.hook.service.author.AuthorService
import com.example.spring.works.hook.util.ApiResponse
import com.example.spring.works.hook.util.ApiResponse.Success
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthorController(private val authorService: AuthorService) {

    @GetMapping(value = ["/authors"], produces = ["application/json"])
    fun findAuthors(): Success<List<AuthorDTO>> {
        val findAuthors = authorService.findAuthors()
        return Success(findAuthors, "작가 & 책 목록들입니다.")
    }

    @GetMapping(value = ["/author/{authorId}"], produces = ["application/json"])
    fun findAuthor(@PathVariable authorId: Long): ApiResponse<AuthorDTO> {
        val findAuthors = authorService.findAuthor(authorId)
        return Success(findAuthors, "작가 & 책 목록입니다.")
    }

    @PostMapping(value = ["/author"], produces = ["application/json"])
    fun registerAuthor(@RequestBody requestDTO: AuthorDTO): Success<AuthorDTO> {
        val saveAuthor = authorService.registerAuthor(requestDTO)
        return Success(saveAuthor, "저장이 완료되었습니다.")
    }

    @PatchMapping(value = ["/author/{authorId}"], produces = ["application/json"])
    fun updateBooksInAuthor(@PathVariable authorId: Long, @RequestBody requestDTO: BookDTO): Success<AuthorDTO> {
        val updateAuthor = authorService.updateBooksInAuthor(authorId, requestDTO)
        return Success(updateAuthor, "작가의 책목록 수정이 완료되었습니다.")
    }

    @DeleteMapping(value = ["/author/{authorId}"], produces = ["application/json"])
    fun deleteAuthor(@PathVariable authorId: Long): Success<String> {
        authorService.deleteAuthor(authorId)
        return Success("OK", "작가 삭제가 완료되었습니다.")
    }
}
