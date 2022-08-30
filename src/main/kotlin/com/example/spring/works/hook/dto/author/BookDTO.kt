package com.example.spring.works.hook.dto.author

import com.example.spring.works.hook.domain.author.Book

data class BookDTO(
    val id: String,
    val title: String,
    val price: Int
) {
    fun toEntity(): Book {
        return Book(
            id = this.id,
            title = this.title,
            price = this.price
        )
    }
}
