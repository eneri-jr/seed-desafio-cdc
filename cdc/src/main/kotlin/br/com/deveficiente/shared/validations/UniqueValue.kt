package br.com.deveficiente.shared.validations

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 1;
    (UniqueValueValidator)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 1
 */

@MustBeDocumented
@Target(FIELD, CONSTRUCTOR, VALUE_PARAMETER)
@Retention(RUNTIME)
@Constraint(validatedBy = [UniqueValueValidator::class])
annotation class UniqueValue(
    val message: String = "The value of this field must be unique, this value is already registered in our system.",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = [],
    val field: String,
    val table: String
)

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 1;
    (UniqueValue)
    * Pontos por branchs: 2;
    (If e teste no return)
    * Pontos função como argumento: 0;

    Total de Pontos: 3
 */

@Singleton
open class UniqueValueValidator(val em: EntityManager) : ConstraintValidator<UniqueValue, Any> {

    @Transactional
    override fun isValid(
        value: Any?,
        annotationMetadata: AnnotationValue<UniqueValue>,
        context: ConstraintValidatorContext
    ): Boolean {

        if (value.toString().isNullOrBlank()) {
            return true
        }

        val field = annotationMetadata.stringValue("field").get()
        val table = annotationMetadata.stringValue("table").get()

        val query = em.createQuery("SELECT 1 FROM $table WHERE $field = :value")
        query.setParameter("value", value.toString())
        val result = query.resultList

        return result!!.isEmpty()
    }
}