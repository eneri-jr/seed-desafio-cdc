package br.com.deveficiente.payment.buy

import br.com.deveficiente.country.Country
import br.com.deveficiente.payment.items.Items
import br.com.deveficiente.shared.validations.CpfOrCnpj
import br.com.deveficiente.state.State
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 4;
    (CpfOrCnpj, Country, State, Items)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 4
 */

@Entity
class Buy(

    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotBlank
    val name: String,

    @field:NotBlank
    val lastName: String,

    @field:NotBlank
    @field:CpfOrCnpj
    val document: String,

    @field:NotBlank
    val address: String,

    @field:NotBlank
    var complement: String,

    @field:NotBlank
    val city: String,

    @field:NotNull
    @ManyToOne
    val country: Country,

    @ManyToOne
    val state: State,

    @field:NotBlank
    val phone: String,

    @field:NotBlank
    val cep: String,

    @field:NotNull
    val total: Double,

    //Ira criar automaticamente uma tabela Buy_Items
    @OneToMany(cascade = [CascadeType.ALL])
    val listItems: List<Items>? = null

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

}