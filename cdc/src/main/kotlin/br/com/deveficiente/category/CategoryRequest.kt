package br.com.deveficiente.category

import br.com.deveficiente.shared.validations.UniqueValue
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 2;
    (UniqueValue)
    (Category)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 2
 */

@Introspected
data class CategoryRequest(

    @field:NotBlank
    @field:UniqueValue(field = "name", table = "Category")
    val name: String

) {
    fun toModel(): Category {
        return Category(name)
    }
}