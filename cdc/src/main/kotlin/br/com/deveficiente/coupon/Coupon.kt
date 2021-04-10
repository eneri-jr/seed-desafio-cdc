package br.com.deveficiente.coupon

import br.com.deveficiente.payment.buy.Buy
import br.com.deveficiente.payment.items.Items
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Future
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 0;
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 0
 */

@Entity
class Coupon(
    @field:NotBlank
    val code: String?,

    @field:NotNull
    @field:Positive
    val percentage: Int?,

    @field:NotNull
    @field:Future
    val expirationDate: LocalDate?
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null
}