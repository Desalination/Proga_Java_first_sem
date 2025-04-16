package com.Lab_5.ClientProject.Exceptions;

public class InvalidLongException extends IllegalArgumentException{
    public InvalidLongException() {
        super("Illegal long argument has been entered. It have to be nature number..");
    }

    public InvalidLongException(String message) {
        super(message);
    }
}
