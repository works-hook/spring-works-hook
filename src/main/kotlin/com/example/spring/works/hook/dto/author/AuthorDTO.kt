package com.example.spring.works.hook.dto.author

import com.example.spring.works.hook.domain.author.Author
import com.example.spring.works.hook.domain.member.Gender

data class AuthorDTO(
    val id: Long?,
    val name: String,
    val gender: Gender,
    val age: Int,
    val books: List<BookDTO>
) {
    fun toEntity(): Author {
        return Author(
            name = this.name,
            gender = this.gender,
            age = this.age,
            books = this.books.map { bookDto -> bookDto.toEntity() }
        )
    }
}
