package com.Lab_5.ServerProject.Commands;

import com.Lab_5.ClientProject.HumanBeing.HumanBeing;
import com.Lab_5.ServerProject.Collection.Data;
import com.Lab_5.ClientProject.Support.InputValidation;

import java.util.LinkedList;
import java.util.ListIterator;

public class Print implements Command {


    public void execute(Data data, LinkedList<String> scripts) {

    }

    public class PrintDescending {

        public static String execute(Data data, LinkedList<String> commands) {
            String response;
            if (data.getSize() != 0) {

                response = "Descending order of elements: \n";
                ListIterator<HumanBeing> it = data.getdataSet().listIterator((int) data.getSize());
                while (it.hasPrevious())
                    response += (it.previous().toString()+"\n");
//                if(it.hasPrevious())
//                    response+="\n";
            }
            else
                response = "Data is empty.";
            commands.removeFirst();
            return response;
        }
    }

    public class Show {
        public static String execute(Data data, LinkedList<String> commands) {
            //InputValidation.comandValidationOneArg(commands);
            String response="";
            if (data.getSize() != 0){
                response = "Increasing order of elements: \n";
                response += print(data.getdataSet(), 1);
        }
            else
                response = "Data is empty.\n";
            commands.removeFirst();
            return response;
        }

        public static String execute(LinkedList<HumanBeing> humans, LinkedList<String> commands) {
            //InputValidation.comandValidationOneArg(commands);
            String response="";
            if (humans.size() != 0){
                response = "Increasing order of elements: \n";
                response += print(humans, 1);
            }
            else
                response = "Data is empty.\n";
            commands.removeFirst();
            return response;
        }
    }


    private static String print(LinkedList<HumanBeing> humans, int id){
        String response = "";
        ListIterator<HumanBeing> it = humans.listIterator((id - 1));
        while (it.hasNext()) {
            response += (it.next().toString())+"\n";
//            if (it.hasNext()){
//                response+="\n";
//            }
        }
        return response;
    }
}
