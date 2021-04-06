package br.com.deveficiente.payment

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Controller("/api/payment")
@Validated
class RegisterPaymentController {

    @Post
    fun register(@Body @Valid paymentRequest: PaymentRequest) : String {
        return paymentRequest.toString()
    }
}