package br.com.deveficiente.author

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.validation.ConstraintViolationException
import javax.validation.Valid

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 3;
    (AuthorRepository, AuthorRequest, Author)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 3
 */

@Controller("/api/author")
@Validated
class RegisterAuthorController(@Inject val authorRepository: AuthorRepository) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Post
    fun register(@Body @Valid authorRequest: AuthorRequest): HttpResponse<Any> {

        logger.info("Author record: ${authorRequest.name} with email: ${authorRequest.email}")

        val newAuthor: Author = authorRequest.toModel()
        authorRepository.save(newAuthor)

        logger.info("Author ${newAuthor.name} sucessfully registered")

        val location = HttpResponse.uri("/api/author/${newAuthor.id}")
        return HttpResponse.created(location)
    }
}