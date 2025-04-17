package com.Lab_5.ServerProject.Exceptions;

public class InputValueNotYesOrNo extends IllegalArgumentException{
    public InputValueNotYesOrNo(){
        super("Input value not \"yes\" or \"no\".");
    }
    public InputValueNotYesOrNo(String message){super(message);}
}
