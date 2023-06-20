package com.skypro.streamemployee.exception;


public class EmployeeAlreadyAddedException extends RuntimeException{
    public EmployeeAlreadyAddedException(String massage) {
        super(massage);
    }
}
