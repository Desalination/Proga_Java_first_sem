package com.Lab_5.ClientProject.Exceptions;

public class LoopingSriptsException extends IllegalArgumentException{
    public LoopingSriptsException() {
        super("Scripts are looping.");
    }

    public LoopingSriptsException(String message) {
        super(message);
    }
}
