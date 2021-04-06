package br.com.deveficiente.payment

import br.com.deveficiente.shared.validations.CpfOrCnpj
import br.com.deveficiente.shared.validations.ExistingObject
import br.com.deveficiente.shared.validations.ValidCountryState
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 3;
    (ExistingObject, CpfOrCnpj, ValidCountryState)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 3
 */

@Introspected
@ValidCountryState
data class PaymentRequest(

    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotBlank
    val name: String,

    @field:NotBlank
    val lastName: String,

    @field:NotBlank
    @field:CpfOrCnpj
    val document: String,

    @field:NotBlank
    val address: String,

    @field:NotBlank
    val complement: String,

    @field:NotBlank
    val city: String,

    @field:NotNull
    @field:ExistingObject(field = "id", table = "Country")
    val idCountry: Long,

    val idState: Long,

    @field:NotBlank
    val phone: String,

    @field:NotBlank
    val cep: String
)