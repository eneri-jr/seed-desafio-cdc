package br.com.deveficiente.payment

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 1;
    (PaymentRequest)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 1
 */

@Controller("/api/payment")
@Validated
class RegisterPaymentController {

    @Post
    fun register(@Body @Valid paymentRequest: PaymentRequest) : String {
        return paymentRequest.toString()
    }
}