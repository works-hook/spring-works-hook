package com.example.spring.works.hook.service.author

import com.example.spring.works.hook.dto.author.AuthorDTO
import com.example.spring.works.hook.dto.author.BookDTO

interface AuthorService {
    fun findAuthors(): List<AuthorDTO>
    fun findAuthor(authorId: Long): AuthorDTO
    fun registerAuthor(requestDTO: AuthorDTO): AuthorDTO
    fun updateBooksInAuthor(authorId: Long, requestDTO: BookDTO): AuthorDTO
    fun deleteAuthor(authorId: Long)
}
