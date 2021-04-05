package br.com.deveficiente.country

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
    (CountryRepository, CountryRequest, Country)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 3
 */

@Controller("/api/country")
@Validated
class RegisterCountryController(val countryRepository: CountryRepository) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Post
    fun register(@Body @Valid countryRequest: CountryRequest): HttpResponse<Any> {
        logger.info("Country record: ${countryRequest.name}")

        val newCountry: Country = countryRequest.toModel()
        countryRepository.save(newCountry)

        logger.info("Country ${newCountry.name} sucessfully registered")

        val location = HttpResponse.uri("/api/author/${newCountry.id}")
        return HttpResponse.created(location)

    }
}