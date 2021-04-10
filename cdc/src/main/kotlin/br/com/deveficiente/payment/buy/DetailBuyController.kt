package br.com.deveficiente.payment.buy

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import org.slf4j.LoggerFactory

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 3;
    (BuyRepository, Buy, DetailBuyResponse)
    * Pontos por branchs: 1;
    (if)
    * Pontos função como argumento: 0;

    Total de Pontos: 4
 */

@Controller("/api/buy")
class DetailBuyController(val buyRepository: BuyRepository) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Get("/{id}")
    fun detail(@PathVariable id: Long): HttpResponse<Any> {

        logger.info("Search payment with id: ${id}.")

        val possibleBuy = buyRepository.findById(id)

        if(!possibleBuy.isPresent)
            return HttpResponse.notFound("Buy not registered in our database.")

        logger.info("Payment found.")

        val detailBuy: DetailBuyResponse = possibleBuy.get().toResponse()

        return HttpResponse.ok(detailBuy)
    }
}