package br.com.deveficiente.shared.validations

import br.com.deveficiente.payment.PaymentRequest
import io.micronaut.core.annotation.AnnotationValue
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.transaction.Transactional

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 1;
    (ValidCountryStateValidator)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 1
 */

@MustBeDocumented
@Target(FIELD, CLASS, TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = [ValidCountryStateValidator::class])
annotation class ValidCountryState(
    val message: String = "The state does not belong to the country.",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 2;
    (ValidCountryState, PaymentRequest)
    * Pontos por branchs: 3;
    (if, if no return, if no return)
    * Pontos função como argumento: 0;

    Total de Pontos: 5
 */

@Singleton
open class ValidCountryStateValidator(val em: EntityManager) : ConstraintValidator<ValidCountryState, PaymentRequest> {

    @Transactional
    override fun isValid(
        value: PaymentRequest,
        annotationMetadata: AnnotationValue<ValidCountryState>,
        context: ConstraintValidatorContext
    ): Boolean {

        if (value.idState.toString().equals("0")) {
            val query = em.createQuery("SELECT 1 FROM State WHERE country_id = :value")
            query.setParameter("value", value.idCountry)
            val result = query.resultList
            return result.isEmpty()
        }

        val query = em.createQuery("SELECT 1 FROM State WHERE country_id = :value AND id = :value2")
        query.setParameter("value2", value.idState)
        query.setParameter("value", value.idCountry)
        val result = query.resultList
        return !result.isEmpty()
    }

}