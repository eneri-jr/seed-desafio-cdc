package br.com.deveficiente.book

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 1;
    (Book)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 1
 */

@Repository
interface BookRepository : JpaRepository<Book, Long>