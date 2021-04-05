package br.com.deveficiente.category

import br.com.deveficiente.shared.validations.UniqueValue
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 1;
    (UniqueValue)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 1
 */

@Entity
class Category(

    @field:NotBlank
    @field:UniqueValue(field = "name", table = "Category")
    val name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}