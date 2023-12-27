package ru.netology.etarakanova.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomerOperationOutOfBoundException extends OperationException {
    public static final String MESSAGE = "Exception while trying to save operation %s for customer %s";
    private int customerId;
    private int operationId;

    @Override
    public String getMessage() {
        return MESSAGE.formatted(operationId, customerId);
    }
}