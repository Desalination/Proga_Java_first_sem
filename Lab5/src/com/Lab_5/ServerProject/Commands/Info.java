package com.Lab_5.ServerProject.Commands;

import com.Lab_5.ServerProject.Collection.Data;
import com.Lab_5.ClientProject.Support.InputValidation;

import java.util.LinkedList;

public class Info {
    public static String execute(Data data, LinkedList<String> commands){
        //InputValidation.comandValidationOneArg(commands);
        commands.removeFirst();
        return data.info()+"\n";
    }
}
