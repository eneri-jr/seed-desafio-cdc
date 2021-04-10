package br.com.deveficiente.payment.buy

import io.micronaut.core.annotation.Introspected

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 0;
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 0
 */

@Introspected
data class DetailBuyResponse(
    val email: String,
    val name: String,
    val lastName: String,
    val document: String,
    val address: String,
    val complement: String,
    val city: String,
    val country: String,
    val state: String,
    val phone: String,
    val cep: String,
    val total: Double,
    val existsCoupon: Boolean,
    val totalFinal: Double
)