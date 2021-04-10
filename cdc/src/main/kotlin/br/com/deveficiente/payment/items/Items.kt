package br.com.deveficiente.payment.items

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 0;
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 0
 */

@Entity
class Items(

    @field:NotNull
    val idProduto: Long,

    @field:NotNull
    @field:Positive
    val amount: Int,

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}