package br.com.deveficiente.payment

import br.com.deveficiente.shared.validations.CpfOrCnpj
import br.com.deveficiente.shared.validations.ExistingObject
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
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

    //Lógica do estado
    val idEstado: Long,

    @field:NotBlank
    val phone: String,

    @field:NotBlank
    val cep: String
)