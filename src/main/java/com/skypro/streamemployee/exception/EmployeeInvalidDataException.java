package com.skypro.streamemployee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeInvalidDataException extends RuntimeException {
    public EmployeeInvalidDataException() {
    }

    public EmployeeInvalidDataException(String message) {
        super(message);
    }

    public EmployeeInvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeInvalidDataException(Throwable cause) {
        super(cause);
    }

    public EmployeeInvalidDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
