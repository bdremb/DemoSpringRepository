package com.example.spring.demospringrest.validation;

import com.example.spring.demospringrest.web.model.OrderFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static org.apache.commons.lang3.ObjectUtils.anyNull;

public class OrderFilterValidValidator implements ConstraintValidator<OrderFilterValid, OrderFilter> {

    @Override
    public boolean isValid(OrderFilter filter, ConstraintValidatorContext constraintValidatorContext) {
        if (anyNull(filter.getPageNumber(), filter.getPageSize())) {
            return false;
        }

        if ((filter.getMinCost() == null && filter.getMaxCost() != null)
                || (filter.getMinCost() != null && filter.getMaxCost() == null)) {
            return false;
        }
        return true;
    }

}
