package br.com.deveficiente.state

import br.com.deveficiente.country.CountryRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import javax.validation.Valid

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 4;
    (CountryRepository, StateRepository, StateRequest, State)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 4
 */

@Controller("/api/state")
@Validated
class RegisterStateController(val countryRepository: CountryRepository, val stateRepository: StateRepository) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Post
    fun register(@Body @Valid stateRequest: StateRequest): HttpResponse<Any> {
        logger.info("State record: ${stateRequest.name}")

        val newState: State = stateRequest.toModel(countryRepository)
        stateRepository.save(newState)

        logger.info("State ${newState.name} sucessfully registered")

        val location = HttpResponse.uri("/api/author/${newState.id}")
        return HttpResponse.created(location)
    }
}