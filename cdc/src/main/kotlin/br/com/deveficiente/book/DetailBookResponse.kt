package br.com.deveficiente.book

import io.micronaut.core.annotation.Introspected
import java.time.LocalDate

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 0;
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 0
 */

@Introspected
data class DetailBookResponse(

    val title: String,
    val resume: String,
    val summary: String,
    val price: Double,
    val pages: Int,
    val isbn: String,
    val publicationDate: LocalDate,
    val category: String,
    val nameAuthor: String,
    val descriptionAuthor: String
)