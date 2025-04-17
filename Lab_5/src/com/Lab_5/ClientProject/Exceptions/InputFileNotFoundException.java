package com.Lab_5.ClientProject.Exceptions;

import java.io.FileNotFoundException;

public class InputFileNotFoundException extends FileNotFoundException {
    public InputFileNotFoundException(String enter_file_name_of){
        super("Error. Input file not found.");
    }
}
