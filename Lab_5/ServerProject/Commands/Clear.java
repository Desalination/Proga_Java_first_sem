package com.Lab_5.ServerProject.Commands;
import com.Lab_5.ServerProject.Collection.Data;
import com.Lab_5.ClientProject.Support.InputValidation;

import java.io.IOException;
import java.util.LinkedList;

public class Clear implements Command {
    public static String execute(Data data, LinkedList<String> scripts) throws IOException {
        //InputValidation.comandValidationOneArg(scripts);
        data.clear();
        scripts.removeFirst();
        return("Data is clear.\n");
    }
}
