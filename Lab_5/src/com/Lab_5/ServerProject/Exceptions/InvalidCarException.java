package com.Lab_5.ServerProject.Exceptions;

public class InvalidCarException  extends IllegalArgumentException{
    public InvalidCarException(){
        super("Error. Not found information about element's having car in form \"yes\" or \"no\".");
    }
    public InvalidCarException(String message){super(message);}
}
