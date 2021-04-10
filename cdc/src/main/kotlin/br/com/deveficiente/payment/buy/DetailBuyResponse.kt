package br.com.deveficiente.payment.buy

import io.micronaut.core.annotation.Introspected

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