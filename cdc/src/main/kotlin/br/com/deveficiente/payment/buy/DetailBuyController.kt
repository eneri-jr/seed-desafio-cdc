package br.com.deveficiente.payment.buy

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable

@Controller("/api/buy")
class DetailBuyController(val buyRepository: BuyRepository) {

    @Get("/{id}")
    fun detail(@PathVariable id: Long): HttpResponse<Any> {
        val possibleBuy = buyRepository.findById(id)

        if(!possibleBuy.isPresent)
            return HttpResponse.notFound("Buy not registered in our database.")

        val detailBuy: DetailBuyResponse = possibleBuy.get().toResponse()

        return HttpResponse.ok(detailBuy)
    }
}