package com.Lab_5.ServerProject.Exceptions;

public class ServerTempNotResponse extends Exception{
    public ServerTempNotResponse(){
        super("Server is temporarily not responding. Try to connect later.");
    }
    public ServerTempNotResponse(String message){super(message);}
}
