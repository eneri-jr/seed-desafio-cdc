package br.com.deveficiente.author

import br.com.deveficiente.shared.validations.UniqueValue
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 2;
    (Author)
    (UniqueValue)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 2
 */

@Introspected
data class AuthorRequest(

    @field:Email
    @field:NotBlank
    @field:UniqueValue(field = "email", table = "Author")
    val email: String,

    @field:NotBlank
    val name: String,

    @field:NotBlank
    @field:Size(max = 400)
    val description: String
) {
    fun toModel(): Author {
        return Author(email, name, description)
    }
}