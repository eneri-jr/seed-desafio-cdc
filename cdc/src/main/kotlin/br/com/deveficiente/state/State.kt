package br.com.deveficiente.state

import br.com.deveficiente.country.Country
import br.com.deveficiente.shared.validations.UniqueValue
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 1;
    (Country)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 1
 */

@Entity
class State(
    @field:NotBlank
    @field:UniqueValue(field = "name", table = "State")
    val name: String,

    @field:ManyToOne
    @field:NotNull
    val country: Country

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}