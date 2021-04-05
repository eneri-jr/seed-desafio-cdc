package br.com.deveficiente.book

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 2;
    (BookRepository, ListBooksResponse)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 2
 */

@Controller("/api/book")
class ListBooksController(val bookRepository: BookRepository) {

    @Get
    fun list(): List<ListBooksResponse> {

        val listBooks = bookRepository.findAll()

        return listBooks.map {
            ListBooksResponse(it.id!!, it.title)
        }
    }
}