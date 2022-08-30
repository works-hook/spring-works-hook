package com.example.spring.works.hook.domain.author

import com.example.spring.works.hook.dto.author.BookDTO
import java.util.UUID

data class Book(
    var id: String = automaticNumbering(),
    val title: String,
    val price: Int,
) {
    fun toBookDto(): BookDTO {
        return BookDTO(
            id = this.id,
            title = this.title,
            price = this.price
        )
    }
}
private fun automaticNumbering() = UUID.randomUUID().toString()
