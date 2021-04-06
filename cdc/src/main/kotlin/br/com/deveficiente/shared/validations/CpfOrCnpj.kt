package br.com.deveficiente.shared.validations

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator
import javax.inject.Singleton
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [CpfOrCnpjValidator::class])
annotation class CpfOrCnpj(
    val message: String = "Documento inválido",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)

@Singleton
class CpfOrCnpjValidator : ConstraintValidator<CpfOrCnpj, Any> {
    override fun isValid(
        value: Any?,
        annotationMetadata: AnnotationValue<CpfOrCnpj>,
        context: ConstraintValidatorContext
    ): Boolean {

        val cpfValido: Boolean = CPFValidator().run {
            initialize(null)
            isValid(value!!.toString(), null)
        }

        val cnpjValido: Boolean = CNPJValidator().run {
            initialize(null)
            isValid(value!!.toString(), null)
        }

        return cpfValido || cnpjValido
    }

}