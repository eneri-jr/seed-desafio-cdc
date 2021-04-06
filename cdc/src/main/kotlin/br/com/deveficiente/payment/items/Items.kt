package br.com.deveficiente.payment.items

import br.com.deveficiente.shared.validations.ExistingObject
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 1;
    (ExistingObject)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 1
 */

@Entity
class Items(

    @field:NotNull
    @field:ExistingObject(field = "id", table = "Book")
    val idProduto: Long,

    @field:NotNull
    @field:Positive
    val amount: Int,

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}