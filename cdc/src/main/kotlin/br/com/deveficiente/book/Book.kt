package br.com.deveficiente.book

import br.com.deveficiente.author.Author
import br.com.deveficiente.category.Category
import br.com.deveficiente.shared.validations.UniqueValue
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.*

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 3;
    (UniqueValue, Author, Category)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 3
 */

@Entity
class Book(

    @field:NotBlank
    @field:UniqueValue(field = "title", table = "Book")
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
    @field:UniqueValue(field = "isbn", table = "Book")
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
}