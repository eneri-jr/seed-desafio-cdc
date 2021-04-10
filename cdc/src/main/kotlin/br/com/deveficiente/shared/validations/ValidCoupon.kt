package br.com.deveficiente.shared.validations

import br.com.deveficiente.coupon.Coupon
import br.com.deveficiente.coupon.CouponRepository
import io.micronaut.core.annotation.AnnotationValue
import javax.inject.Singleton
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.reflect.KClass
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import java.time.LocalDate
import javax.persistence.EntityManager
import javax.transaction.Transactional

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 1;
    (ValidCouponValidator)
    * Pontos por branchs: 0;
    * Pontos função como argumento: 0;

    Total de Pontos: 1
 */

@MustBeDocumented
@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = [ValidCouponValidator::class])
annotation class ValidCoupon (

    val message: String = "Invalid Coupon",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)

/*
- Pela técnica do CDD temos nesta classe:
    * Pontos por acoplamento: 3;
    (CouponRepository, ValidCoupon, Coupon)
    * Pontos por branchs: 3;
    (if, if,  if no return)
    * Pontos função como argumento: 0;

    Total de Pontos: 6
 */

@Singleton
open class ValidCouponValidator (val couponRepository: CouponRepository) : ConstraintValidator<ValidCoupon, String> {

    @Transactional
    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<ValidCoupon>,
        context: ConstraintValidatorContext
    ): Boolean {
        if(value.isNullOrBlank())
            return true

        val possibleCoupon: Coupon? = couponRepository.findByCode(value.toString())

        if(possibleCoupon == null)
            return false;

        val date = LocalDate.now()
        return possibleCoupon.expirationDate!!.isAfter(date)
    }

}