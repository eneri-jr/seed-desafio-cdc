package br.com.deveficiente.payment

import br.com.deveficiente.book.BookRepository
import br.com.deveficiente.country.CountryRepository
import br.com.deveficiente.coupon.CouponRepository
import br.com.deveficiente.payment.buy.Buy
import br.com.deveficiente.payment.buy.BuyRepository
import br.com.deveficiente.state.StateRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 6;
    (BookRepository, CountryRepository, StateRepository, BuyRepository, PaymentRequest, Buy)
    * Pontos por branchs: 1;
    (If)
    * Pontos função como argumento: 0;

    Total de Pontos: 7
 */

@Controller("/api/payment")
@Validated
class RegisterPaymentController(
    val bookRepository: BookRepository,
    val countryRepository: CountryRepository,
    val stateRepository: StateRepository,
    val buyRepository: BuyRepository,
    val couponRepository: CouponRepository
) {

    @Post
    fun register(@Body @Valid paymentRequest: PaymentRequest) : HttpResponse<Any>{
        val valid: Boolean = paymentRequest.shoppingCart.validTotal(bookRepository)
        if (!valid)
            return HttpResponse.badRequest("The shopping cart total is invalid.")

        val newBuy: Buy = paymentRequest.toModel(countryRepository, stateRepository, couponRepository)
        buyRepository.save(newBuy)

        val location = HttpResponse.uri("/api/author/${newBuy.id}")
        return HttpResponse.created(location)
    }

}