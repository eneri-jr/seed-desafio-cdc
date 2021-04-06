package br.com.deveficiente.coupon

import br.com.deveficiente.shared.validations.UniqueValue
import io.micronaut.core.annotation.Introspected
import java.time.LocalDate
import javax.validation.constraints.Future
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 2;
    (UniqueValue, Coupon)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 2
 */

@Introspected
data class CouponRequest(

    @field:NotBlank
    @field:UniqueValue(field = "code", table = "Coupon")
    val code: String,

    @field:NotNull
    @field:Positive
    val percentage: Int,

    @field:NotNull
    @field:Future
    val expirationDate: LocalDate
) {
    fun toModel() : Coupon {
        return Coupon(code, percentage, expirationDate)
    }
}