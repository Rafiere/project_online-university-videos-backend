package dev.piccodev.domain.validation.handler;

import dev.piccodev.domain.exception.DomainException;
import dev.piccodev.domain.validation.Error;
import dev.piccodev.domain.validation.Validation;
import dev.piccodev.domain.validation.ValidationHandler;

import java.util.List;

/**
 * This handler will throw an exception always that an error is received.
 * This handler will not be cumulative. In this handler, we will not implement the "Notification Pattern".
 */

public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(Error error) {
        throw DomainException.with(error);
    }

    @Override
    public ValidationHandler append(ValidationHandler validationHandler) {

        throw DomainException.with(validationHandler.getErrors());
    }

    @Override
    public ValidationHandler validate(Validation validation) {
        try {
            validation.validate();
            return this;
        } catch (DomainException e){
            throw DomainException.with(List.of(new Error(e.getMessage())));
        }
    }

    @Override
    public List<Error> getErrors() {
        return null;
    }
}
