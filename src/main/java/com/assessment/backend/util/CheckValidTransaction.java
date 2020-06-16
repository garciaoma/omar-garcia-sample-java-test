package com.assessment.backend.util;

import com.assessment.backend.util.validator.TransactionValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TransactionValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckValidTransaction {
    String message() default "Transaction information should be valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
