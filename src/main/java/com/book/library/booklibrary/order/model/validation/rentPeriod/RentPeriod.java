package com.book.library.booklibrary.order.model.validation.rentPeriod;

import com.book.library.booklibrary.order.enums.OrderType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RentPeriodValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RentPeriod {

    String message() default "Invalid rent time period!";

    String orderType();

    String rentTime();

    int min() default 4;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
