package com.Lab_5.ServerProject.Exceptions;

public class NotFoundIdException extends IllegalArgumentException{
    public NotFoundIdException(){
        super("Error. Not found element with this id.");
    }
    public NotFoundIdException(String message){super(message);}
}
