package br.com.deveficiente.author

import br.com.deveficiente.shared.validations.UniqueValue
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
    * Pontos por acoplamento: 1;
    (UniqueValue)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 1
 */

@Entity
class Author(

    @field:Email
    @field:UniqueValue(field = "email", table = "Author")
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