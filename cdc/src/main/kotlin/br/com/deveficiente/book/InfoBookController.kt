package br.com.deveficiente.book

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 5;
    (BookRepository, Book, ListBooksResponse, if, DetailResponseBook)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 5
 */

@Controller("/api/book")
class InfoBookController(val bookRepository: BookRepository) {

    @Get
    fun list(): List<ListBooksResponse> {

        val listBooks = bookRepository.findAll()

        return listBooks.map {
            ListBooksResponse(it.id!!, it.title)
        }
    }

    @Get("/{id}")
    fun detail(@PathVariable id: Long): HttpResponse<Any> {

        val possibleBook = bookRepository.findById(id)

        if (!possibleBook.isPresent)
            return HttpResponse.notFound("Book not registered in our database.")

        val detailBook: DetailBookResponse = possibleBook.get().toResponse()

        return HttpResponse.ok(detailBook)
    }
}