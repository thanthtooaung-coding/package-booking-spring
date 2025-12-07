package com.vinn.packagebooking.common.exception;

public class InvalidFieldException extends RuntimeException {
    private final String field;
    private final String message;

    public InvalidFieldException(String field, String message) {
        super("Invalid field '" + field + "': " + message);
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

