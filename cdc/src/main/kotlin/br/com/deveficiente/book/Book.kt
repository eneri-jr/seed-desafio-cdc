package br.com.deveficiente.book

import br.com.deveficiente.author.Author
import br.com.deveficiente.category.Category
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.*

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 3;
    (Category, Author, DetailBookResponse)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 3
 */

@Entity
class Book(

    @field:NotBlank
    val title: String,

    @field:NotBlank
    @field:Size(max = 500)
    val resume: String,

    @field:NotBlank
    val summary: String,

    @field:NotNull
    @field:Min(20)
    val price: Double,

    @field:NotNull
    @field:Min(100)
    val pages: Int,

    @field:NotBlank
    val isbn: String,

    @field:NotNull
    @field:Future
    val publicationDate: LocalDate,

    @field:ManyToOne
    @field:NotNull
    val category: Category,

    @field:ManyToOne
    @field:NotNull
    val author: Author
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun toResponse(): DetailBookResponse {
        return DetailBookResponse(
            title, resume, summary, price, pages, isbn, publicationDate, category.name,
            author.name, author.description
        )
    }
}