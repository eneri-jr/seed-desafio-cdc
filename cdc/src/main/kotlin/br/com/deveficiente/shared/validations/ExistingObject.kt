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
    (ExistingObjectValidator)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 1
 */

@MustBeDocumented
@Target(FIELD, CONSTRUCTOR, VALUE_PARAMETER)
@Retention(RUNTIME)
@Constraint(validatedBy = [ExistingObjectValidator::class])
annotation class ExistingObject(

    val message: String = "Informed id is not registered in the bank.",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = [],
    val field: String,
    val table: String
)

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 1;
    (ExistingObject)
    * Pontos por branchs: 1;
    (Teste no return)
    * Pontos função como argumento: 0;

    Total de Pontos: 2
 */

@Singleton
open class ExistingObjectValidator(val em: EntityManager) : ConstraintValidator<ExistingObject, Any> {

    @Transactional
    override fun isValid(
        value: Any?,
        annotationMetadata: AnnotationValue<ExistingObject>,
        context: ConstraintValidatorContext
    ): Boolean {

        val field = annotationMetadata.stringValue("field").get()
        val table = annotationMetadata.stringValue("table").get()

        val query = em.createQuery("SELECT 1 FROM $table WHERE $field = :value")
        query.setParameter("value", value)
        val result = query.resultList

        return !result!!.isEmpty()
    }

}