package com.example.spring.demospringrest.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = OrderFilterValidValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderFilterValid {

    String message() default "Поля пагинации должны быть указаны, если указаны minCost и maxCost!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
