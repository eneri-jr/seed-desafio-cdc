package br.com.deveficiente.book

import io.micronaut.core.annotation.Introspected

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 0;
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 0
 */

@Introspected
data class ListBooksResponse(
    val id: Long,
    val title: String
)