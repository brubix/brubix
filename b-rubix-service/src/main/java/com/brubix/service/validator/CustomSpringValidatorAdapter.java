package com.brubix.service.validator;

import com.brubix.common.exception.CustomMethodArgumentNotValidException;
import com.brubix.common.exception.error.ErrorCode;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

public abstract class CustomSpringValidatorAdapter<T> {

    private SpringValidatorAdapter springValidatorAdapter;

    public CustomSpringValidatorAdapter(SpringValidatorAdapter springValidatorAdapter) {
        this.springValidatorAdapter = springValidatorAdapter;
    }

    public void doValidate(T t) {
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(t, t.getClass().getName());
        springValidatorAdapter.validate(t, errors);
        if (errors.hasErrors()) {
            throw new CustomMethodArgumentNotValidException(errors, getErrorCode());
        }

    }

    public abstract ErrorCode getErrorCode();
}
