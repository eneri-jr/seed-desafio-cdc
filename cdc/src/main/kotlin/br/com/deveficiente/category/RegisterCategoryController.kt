package br.com.deveficiente.category

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
    (CategoryRepository, CategoryRequest, Category)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 3
 */

@Controller("/api/category")
@Validated
class RegisterCategoryController(val categoryRepository: CategoryRepository) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Post
    fun register(@Body @Valid categoryRequest: CategoryRequest) : HttpResponse<Any> {
        logger.info("Category record: ${categoryRequest.name}")

        val newCategory: Category = categoryRequest.toModel()
        categoryRepository.save(newCategory)

        logger.info("Category ${newCategory.name} sucessfully registered")

        val location = HttpResponse.uri("/api/author/${newCategory.id}")
        return HttpResponse.created(location)
    }
}