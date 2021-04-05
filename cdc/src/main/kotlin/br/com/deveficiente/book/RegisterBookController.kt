package br.com.deveficiente.book

import br.com.deveficiente.author.AuthorRepository
import br.com.deveficiente.category.CategoryRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import javax.validation.Valid

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 5;
    (AuthorRepository, BookRepository, CategoryRepository, BookRequest, Book)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 5
 */

@Controller("/api/book")
@Validated
class RegisterBookController(
    val authorRepository: AuthorRepository,
    val bookRepository: BookRepository,
    val categoryRepository: CategoryRepository
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Post
    fun register(@Body @Valid bookRequest: BookRequest): HttpResponse<Any> {

        logger.info("Book record: ${bookRequest.title} with isbn: ${bookRequest.isbn}")

        val newBook: Book = bookRequest.toModel(authorRepository, categoryRepository)
        bookRepository.save(newBook)

        logger.info("Book ${newBook.title} sucessfully registered")

        val location = HttpResponse.uri("/api/author/${newBook.id}")
        return HttpResponse.created(location)
    }
}