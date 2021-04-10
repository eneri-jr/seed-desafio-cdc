package br.com.deveficiente.state

import br.com.deveficiente.country.CountryRepository
import br.com.deveficiente.shared.validations.ExistingObject
import br.com.deveficiente.shared.validations.UniqueValue
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 2;
    (CountryRepository, Country)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 2
 */

@Introspected
data class StateRequest(
    @field:NotBlank
    @field:UniqueValue(field = "name", table = "State")
    val name: String,

    @field:NotNull
    @field:ExistingObject(field = "id", table = "Country")
    val idCountry: Long
) {
    fun toModel(countryRepository: CountryRepository): State {
        val country = countryRepository.findById(idCountry)
        return State(name, country.get())
    }
}