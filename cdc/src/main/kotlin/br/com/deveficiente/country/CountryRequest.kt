package br.com.deveficiente.country

import br.com.deveficiente.shared.validations.UniqueValue
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 0;
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 0
 */

@Introspected
data class CountryRequest(

    @field:NotBlank
    @field:UniqueValue(field = "name", table = "Country")
    val name: String
)