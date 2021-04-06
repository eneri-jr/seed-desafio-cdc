package br.com.deveficiente.coupon

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import javax.validation.Valid

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 3;
    (CouponRepository, CouponRequest, Coupon)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 3
 */

@Controller("/api/coupon")
@Validated
class RegisterCouponController(val couponRepository: CouponRepository) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Post
    fun register(@Body @Valid couponRequest: CouponRequest): HttpResponse<Any> {

        logger.info("Coupon record: ${couponRequest.code} with expiration: ${couponRequest.expirationDate}")

        val newCoupon: Coupon = couponRequest.toModel()
        couponRepository.save(newCoupon)

        logger.info("Coupon ${newCoupon.code} sucessfully registered")

        val location = io.micronaut.http.HttpResponse.uri("/api/author/${newCoupon.id}")
        return io.micronaut.http.HttpResponse.created(location)
    }
}