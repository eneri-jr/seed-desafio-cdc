package br.com.deveficiente.coupon

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 1;
    (Coupon)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 1
 */

@Repository
interface CouponRepository: JpaRepository<Coupon, Long> {

    fun findByCode(code: String) : Coupon?
}