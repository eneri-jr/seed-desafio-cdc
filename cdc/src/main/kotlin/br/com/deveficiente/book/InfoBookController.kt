package br.com.deveficiente.book

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import org.slf4j.LoggerFactory

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 4;
    (BookRepository, Book, ListBooksResponse, DetailResponseBook)
    * Pontos por branchs: 2;
    (map, if)
    * Pontos função como argumento: 0;

    Total de Pontos: 6
 */

@Controller("/api/book")
class InfoBookController(val bookRepository: BookRepository) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Get
    fun list(): List<ListBooksResponse> {

        logger.info("Listing all registered books.")

        val listBooks = bookRepository.findAll()

        return listBooks.map {
            ListBooksResponse(it.id!!, it.title)
        }
    }

    @Get("/{id}")
    fun detail(@PathVariable id: Long): HttpResponse<Any> {

        logger.info("Search book with id: ${id}.")

        val possibleBook = bookRepository.findById(id)

        if (!possibleBook.isPresent)
            return HttpResponse.notFound("Book not registered in our database.")

        logger.info("Book found.")

        val detailBook: DetailBookResponse = possibleBook.get().toResponse()

        return HttpResponse.ok(detailBook)
    }
}