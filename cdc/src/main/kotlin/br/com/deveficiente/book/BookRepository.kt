package br.com.deveficiente.book

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface BookRepository : JpaRepository<Book, Long>