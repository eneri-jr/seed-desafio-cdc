package br.com.deveficiente.author

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 0;
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 0
 */

@Entity
class Author(

    @field:Email
    val email: String,

    @field:NotBlank
    val name: String,

    @field:NotBlank
    @field:Size(max = 400)
    val description: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    val createdAt = LocalDateTime.now()
}