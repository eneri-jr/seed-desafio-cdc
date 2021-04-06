package br.com.deveficiente.payment

import br.com.deveficiente.book.BookRepository
import br.com.deveficiente.shared.validations.ExistingObject
import io.micronaut.core.annotation.Introspected
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 2;
    (ItemsRequest, BookRepository)
    * Pontos por branchs: 2;
    (map, if do return)
    * Pontos função como argumento: 0;

    Total de Pontos: 4
 */

@Introspected
data class ShoppingCartRequest(

    @field:NotNull
    @field:Positive
    val total: Double,

    @field:Size(min = 1)
    @field:Valid
    val listItems: List<ItemsRequest>
) {
    fun validTotal(bookRepository: BookRepository) : Boolean {
        var sum: Double = 0.0

        listItems.map {
            var result = bookRepository.findById(it.id)
            sum += (result.get().price * it.amount)
        }

        return sum == total
    }
}

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 1;
    (ExistingObject)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 1
 */

@Introspected
data class ItemsRequest(
    @field:NotNull
    @field:ExistingObject(field = "id", table = "Book")
    val id: Long,

    @field:NotNull
    @field:Positive
    val amount: Int
)