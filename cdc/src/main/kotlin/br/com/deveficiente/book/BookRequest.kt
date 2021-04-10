package br.com.deveficiente.book

import br.com.deveficiente.author.AuthorRepository
import br.com.deveficiente.category.CategoryRepository
import br.com.deveficiente.shared.validations.ExistingObject
import br.com.deveficiente.shared.validations.UniqueValue
import io.micronaut.core.annotation.Introspected
import java.time.LocalDate
import javax.validation.constraints.*

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 5;
    (Author, Category, AuthorRepository, CategoryRepository, Book)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 5
 */

@Introspected
data class BookRequest(

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

    @field:NotNull
    @field:ExistingObject(field = "id", table = "Category")
    val idCategory: Long,

    @field:NotNull
    @field:ExistingObject(field = "id", table = "Author")
    val idAuthor: Long
) {
    fun toModel(authorRepository: AuthorRepository, categoryRepository: CategoryRepository): Book {
        val category = categoryRepository.findById(idCategory)
        val author = authorRepository.findById(idAuthor)

        return Book(title, resume, summary, price, pages, isbn, publicationDate, category.get(), author.get())
    }

}