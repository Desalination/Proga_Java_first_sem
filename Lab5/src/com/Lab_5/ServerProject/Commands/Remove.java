package com.Lab_5.ServerProject.Commands;

import com.Lab_5.ClientProject.HumanBeing.HumanBeing;
import com.Lab_5.ServerProject.Collection.Data;
import com.Lab_5.ClientProject.Support.InputValidation;
import com.Lab_5.ServerProject.Exceptions.NotFoundIdException;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static com.Lab_5.ClientProject.Support.InputConsole.YESorNO;

public class Remove implements Command {
    public class RemoveById {
        public static String execute(Data data, LinkedList<String> commands) {
            //1. Такого айди может не сущестовать, ну и что))
            long id = Long.parseLong(commands.getFirst().split(" ")[1]);
            commands.removeFirst();//сразу удалаяем имя команды
            HumanBeing del_hum = data.get(id);
            //Boolean ifYes = InputValidation.ifYESorNO(commands);
            String response = "";
            //2. Айди не натуральное > 0
            if (del_hum != null) {
                data.removeByIdInclusive(id, 1);
                response = "Element by id{" + id + "}" + " was removed.";

            } else {
                ListIterator<HumanBeing> it = data.getdataSet().listIterator(0);
                String ides = "";
                while (it.hasNext()) {
                    ides += ("{" + it.next().getId().toString() + "} ");
                }
                throw new NotFoundIdException("Removing by id canceled. Element by id={"+id+"} don't exist.\n" +
                        "Existing identifiers: "+
                        (data.getdataSet().size()!=0 ? ides : "don't exist"));
            }
            data.setId();
            return response+"\n";
        }
    }

    public class RemoveFirst {
        public static String execute(Data data, LinkedList<String> commands) {

            //RemoveById.execute(data, commands);
            //InputValidation.comandValidationOneArg(commands);
            commands.removeFirst();//удалили команду
            //Boolean ifYes = InputValidation.ifYESorNO(commands);
            String response = "";
            if(data.getdataSet().size() == 0) {
                throw new NotFoundIdException("There is no element for removing. Collection is empty.");
            }
            else {
                HumanBeing hum = data.getdataSet().removeFirst();
                response = ("First element "+ hum.toString() + " was removed.");
            }

            data.setId();
            return response+"\n";
        }
    }

    public class RemoveGreater {
        public static String execute(Data data, LinkedList<String> commands) {//доделать
            if(data.getdataSet().size() == 0)
                throw new NotFoundIdException("There is no element for removing. Collection is empty.");
            long id = Long.parseLong(commands.getFirst().split(" ")[0]);
            commands.removeFirst();//удалили имя команды
//            Boolean ifYes = InputValidation.ifYESorNO(commands);
            if (id < data.getdataSet().getLast().getId()) {
                data.removeByIdNonInclusive(id, data.getSize());
                data.setId();
                commands.removeFirst();
                return("Elements by id greater {" + id + "}" + " were removed.\n");
            }
            else {
                return("Elements greater than Person[id=" + id + "] don't exist.\n");
//                if (ifYes != null)
//                    commands.removeFirst();
            }
        }
    }


    private static void removeInteractive(Data data, long id) {
        if (YESorNO()) {
            data.removeByIdNonInclusive(id, data.getSize());
            System.out.println("Elements by id greater {" + id + "}" + " were removed.");
        } else
            System.out.println("The remove operation was canceled");
    }

    public class RemoveHead {
        public static String execute(Data data, LinkedList<String> commands) {
            //InputValidation.comandValidationOneArg(commands);
            commands.removeFirst();
//            Boolean ifYes = InputValidation.ifYESorNO(commands);
            if(data.getdataSet().size() == 0) {
                throw new NotFoundIdException("There is no element for removing. Collection is empty.");
            }
            data.getdataSet().removeFirst();
            data.setId();
            return("First element was removed.\n");

        }//method
    }//smallclass
}//Bigclass
