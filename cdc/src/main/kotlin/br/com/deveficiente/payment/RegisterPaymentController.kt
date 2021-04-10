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
import org.slf4j.LoggerFactory
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Valid

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 5;
    (BookRepository, CouponRepository, BuyRepository, PaymentRequest, Buy)
    * Pontos por branchs: 1;
    (If)
    * Pontos função como argumento: 0;

    Total de Pontos: 6
 */

@Controller("/api/payment")
@Validated
class RegisterPaymentController(
    val bookRepository: BookRepository,
    val em: EntityManager,
    val buyRepository: BuyRepository,
    val couponRepository: CouponRepository
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Post
    @Transactional
    fun register(@Body @Valid paymentRequest: PaymentRequest) : HttpResponse<Any>{

        logger.info("Payment record")
        val valid: Boolean = paymentRequest.shoppingCart.validTotal(bookRepository)
        if (!valid)
            return HttpResponse.badRequest("The shopping cart total is invalid.")

        val newBuy: Buy = paymentRequest.toModel(em, couponRepository)
        buyRepository.save(newBuy)

        logger.info("Payment ${newBuy.id} sucessfully registered")

        val location = HttpResponse.uri("/api/author/${newBuy.id}")
        return HttpResponse.created(location)
    }

}