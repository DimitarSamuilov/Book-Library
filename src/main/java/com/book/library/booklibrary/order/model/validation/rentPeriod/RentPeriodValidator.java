package com.book.library.booklibrary.order.model.validation.rentPeriod;

import com.book.library.booklibrary.order.enums.OrderType;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class RentPeriodValidator implements ConstraintValidator<RentPeriod, Object> {

    private String orderTypeValue;
    private String rentPeriodValue;
    private int minPeriodValue;

    @Override
    public void initialize(RentPeriod constraintAnnotation) {
        this.orderTypeValue = constraintAnnotation.orderType();
        this.rentPeriodValue = constraintAnnotation.rentTime();
        this.minPeriodValue = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        String fieldOrderType = (String) new BeanWrapperImpl(value).getPropertyValue(this.orderTypeValue);
        if (fieldOrderType.equalsIgnoreCase(OrderType.RENT.toString())) {
            String rentPeriod = (String) new BeanWrapperImpl(value).getPropertyValue(this.rentPeriodValue);
            try {
                Integer finalRentPeriod = Integer.valueOf(rentPeriod);
                if (finalRentPeriod >= this.minPeriodValue) {
                    return true;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }

}
