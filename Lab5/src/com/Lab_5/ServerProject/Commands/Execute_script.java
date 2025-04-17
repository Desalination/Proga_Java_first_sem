package com.Lab_5.ServerProject.Commands;

import com.Lab_5.ClientProject.Client.MyCommand;
import com.Lab_5.ServerProject.Exceptions.InputFileNotFoundException;
import com.Lab_5.ServerProject.Server.Call_commands;
import com.Lab_5.ServerProject.Collection.Data;
import com.Lab_5.ClientProject.Exceptions.LoopingSriptsException;
import com.Lab_5.ClientProject.Exceptions.ScriptContentException;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

public class Execute_script implements Command {
    private static LinkedList<String> commands = new LinkedList<>();


    public static String execute(Data data, MyCommand myCommand) throws IOException, InputFileNotFoundException {
    //какие данные получаю и какие посылаю?
        //изначально там, май комманд с args: name, script1, script2, ...
//        if(scripts.getFirst().split(" ")[0].equals("execute_script"))
//            scripts.removeFirst();
        if(myCommand.getArgs().getFirst().equals("execute_script"))
            myCommand.getArgs().removeFirst();//удалили имя команды из аргс
        String response = "";
        String com = myCommand.getArgs().getFirst();//прочитали первый скрипт

        if (commands.contains(com)) {
            throw new LoopingSriptsException();
        }

        commands.add(com);//кинули прочитанную строку всего скрипта
        try {
            String comWithout_r = com.replaceAll("[\r]", "");
            String[] comSplitedBy_n = comWithout_r.split("\n");
            LinkedList<String> newScripts = new LinkedList<>();
            Collections.addAll(newScripts, comSplitedBy_n);
            //получили разделенный скрипт
            myCommand.getArgs().removeFirst();//удалили первый скрипт, который отправили на выполнение
            int scriptNumber = (myCommand.getArgs().size() + 1);
            response += Call_commands.call(myCommand, newScripts, data);//кидаем массив построчных строк скрипта на выполнение

            commands.remove(com);
            response += "Script [" + scriptNumber + "] performed succeed.\n";
            return response;
        } catch (Exception ex) {
            commands.remove(com);
            throw ex;
        }
    }
}
