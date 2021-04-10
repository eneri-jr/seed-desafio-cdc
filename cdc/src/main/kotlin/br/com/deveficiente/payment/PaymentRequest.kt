package br.com.deveficiente.payment

import br.com.deveficiente.country.CountryRepository
import br.com.deveficiente.payment.items.Items
import br.com.deveficiente.payment.buy.Buy
import br.com.deveficiente.shared.validations.CpfOrCnpj
import br.com.deveficiente.shared.validations.ExistingObject
import br.com.deveficiente.shared.validations.ValidCountryState
import br.com.deveficiente.shared.validations.ValidCoupon
import br.com.deveficiente.state.StateRepository
import io.micronaut.core.annotation.Introspected
import java.util.stream.Collectors
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 5;
    (ShoppingCartRequest, CountryRepository, StateRepository, Buy, Items)
    * Pontos por branchs: 1;
    (map)
    * Pontos função como argumento: 0;

    Total de Pontos: 6
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
    val cep: String,

    @field:NotNull
    @field:Valid
    val shoppingCart: ShoppingCartRequest,

    @field:NotBlank
    @field:ValidCoupon()
    val coupon: String

) {
    fun toModel(countryRepository: CountryRepository, stateRepository: StateRepository): Buy {
        val country = countryRepository.findById(idCountry)
        val state = stateRepository.findById(idState)

        val newListItems: MutableList<Items> = shoppingCart.listItems.stream().map {
            Items(it.id, it.amount)
        }.collect(Collectors.toList())

        return Buy(
            email,
            name,
            lastName,
            document,
            address,
            complement,
            city,
            country.get(),
            state.get(),
            phone,
            cep,
            shoppingCart.total,
            newListItems
        )

    }
}