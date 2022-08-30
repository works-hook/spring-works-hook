package com.example.spring.works.hook.domain.author

import com.example.spring.works.hook.domain.member.Gender
import com.example.spring.works.hook.domain.util.BaseEntity
import com.example.spring.works.hook.dto.author.AuthorDTO
import com.example.spring.works.hook.dto.author.BookDTO
import com.vladmihalcea.hibernate.type.json.JsonType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@TypeDef(name = "json", typeClass = JsonType::class)
data class Author(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val gender: Gender,
    val age: Int,

    @Type(type = "json")
    @Column(columnDefinition = "json")
    var books: List<Book>

) : BaseEntity() {

    fun addBook(book: Book) {
        books = books.plus(book)
    }

    fun toAuthorDto(): AuthorDTO {
        return AuthorDTO(
            id = this.id,
            name = this.name,
            gender = this.gender,
            age = this.age,
            books = this.books.map { book -> book.toBookDto() }
        )
    }

    fun changeBooks(requestDTO: BookDTO) {
        books = listOf(requestDTO.toEntity())
    }
}
